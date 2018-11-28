package perform.dataimport.action;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;
import perform.norm.dao.PKpinormDAO;
import perform.norm.pojo.PKpinorm;
import perform.position.dao.PPositionDAO;
import perform.position.pojo.PPosition;
import perform.seasonrate.dao.PKPIScoreDAO;
import perform.seasonrate.dao.PScoreDAO;
import perform.seasonrate.pojo.PKPIScore;
import perform.seasonrate.pojo.PScore;
import perform.userinfo.dao.PUserDAO;
import perform.userinfo.pojo.PUser;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import ccb.hibernate.HibernateSessionFactory;
public class ImportKpiScore  extends ActionSupport implements ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ImportKpiScore.class);
	private File file; //上传的文件
    private String fileFileName;
    private String fileContentType;
    private String message;
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
public String execute() throws Exception {
		
		String realpath = "D:/import/performance/";
		message = "导入成功";
		//UserInfoDAO uidao = new UserInfoDAO();
		
		int nn=0;
		if (file != null) {
	       File savefile = new File(new File(realpath), fileFileName);
	       if (!savefile.getParentFile().exists())
	            savefile.getParentFile().mkdirs();
	           FileUtils.copyFile(file, savefile);
	           
	     }
	    else
	    {
	    	message="传入文件有误";
	    	//this.addFieldError("用户","传入文件有误");
			return "failed";
	    }
		String yearmonth = fileFileName.substring(0, 6);
		if(yearmonth.compareTo("201001")<0||yearmonth.compareTo("209912")>0)
		{
			message="文件名不符合规范";
			//this.addFieldError("用户","文件名不符合规范");
			return "failed";
		}
	
			Session session = HibernateSessionFactory.getSession();
	    	Transaction trans=session.beginTransaction();
	    	//AssetInfoDAO kidao = new AssetInfoDAO();
	    	try {
	    		Workbook book = Workbook.getWorkbook(new File(realpath+fileFileName));
				// 获得第一个工作表对象
				Sheet sheet = book.getSheet(0);
				// 得到第一列第一行的单元格
				nn = sheet.getRows();
				PFlagDAO fdao = new PFlagDAO();
				PFlag flag = fdao.findByIsNew(1);
				//String sql = "delete from p_kpiscore where year='"+flag.getYear()+"' and season='"+flag.getSeason()+"'";
				//session.createSQLQuery(sql).executeUpdate();
				for (int i = 1; i < nn; i++) {
					
					PKPIScore pkp = new PKPIScore();
					PKPIScoreDAO pkpdao = new PKPIScoreDAO();
					PScore ps = new PScore();
					PScoreDAO psdao = new PScoreDAO();
					String newnumber = sheet.getCell(0, i).getContents().trim();
					//AssetInfo ki = new AssetInfo();
					//String strsum = sheet.getCell(1, i).getContents().trim();
					if(newnumber==null||newnumber.equals("")||newnumber.isEmpty())
					{
//						message="导入的新一代编号为空";
//						return "failed";
					}
					else
					{
						if(sheet.getCell(1, i).getContents().trim()==null||sheet.getCell(2, i).getContents().trim().equals(""))
						{
							message="导入的分值为空";
							return "failed";
						}
						double sum =Double.parseDouble(sheet.getCell(2, i).getContents().trim());
						
						
						pkp = pkpdao.findByNewNumbernew(newnumber,flag.getYear(),flag.getSeason());
						ps = psdao.findByNewnumberYearSeasonnew(newnumber, flag.getYear(), flag.getSeason());
						if(pkp==null)
						{
							message="导入的新一代编号"+newnumber+"有误";
							//this.addFieldError("用户","导入失败");
							return "failed";
						}
						else
					    {
							pkp.setSum(sum);
					    }
						if(ps==null)
						{
							message="导入的新一代编号"+newnumber+"有误";
							//this.addFieldError("用户","导入失败");
							return "failed";
						}
						else
					    {
							ps.setKpiscore(sum);
					    }
						    pkpdao.merge(pkp);
						    psdao.merge(ps);
		    	        }
					}
				}
				catch (Exception e) {
				trans.rollback();//出错回滚
				e.printStackTrace();
			    }
				finally{
				 trans.commit();
		         session.flush();
		         session.clear();
		         session.close();
			  }
			//countKqjlDaily();//计算考勤记录日表
			//countKqjlDailyForYgxy();//计算考勤记录日表(员工响应)
			//countkqjlMonth();//计算考勤记录月表
		
		return "success";
   }
public void setServletResponse(HttpServletResponse arg0) {
	// TODO Auto-generated method stub
	
}
}
