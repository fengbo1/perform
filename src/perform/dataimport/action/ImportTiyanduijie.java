package perform.dataimport.action;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import perform.flag.dao.PFlagDAO;
import perform.flag.pojo.PFlag;
import perform.seasonrate.dao.PKCIScoreDAO;
import perform.seasonrate.dao.PScoreDAO;
import perform.seasonrate.pojo.PKCIScore;
import perform.seasonrate.pojo.PScore;
import perform.userinfo.dao.PUserDAO;
import ccb.hibernate.HibernateSessionFactory;

public class ImportTiyanduijie {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ImportKciScore.class);
	private File file; //上传的文件
    private String fileFileName;
    private String fileContentType;
    private String message;
    private String newnumber;
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
	public String getNewnumber() {
		return newnumber;
	}
	public void setNewnumber(String newnumber) {
		this.newnumber = newnumber;
	}
public String execute() throws Exception {
		
		String realpath = "D:/import/performance/";
		message = "导入成功";
		PKCIScoreDAO pkcdao = new PKCIScoreDAO();
		PScoreDAO psdao = new PScoreDAO();
		int nn=0;
		int mm=0;
		if (file != null) {
	       File savefile = new File(new File(realpath), fileFileName);
	       if (!savefile.getParentFile().exists())
	            savefile.getParentFile().mkdirs();
	           FileUtils.copyFile(file, savefile);
	     }
	    else
	    {
	    	message="传入文件有误";
			return "failed";
	    }
		String yearmonth = fileFileName.substring(0, 6);
		if(yearmonth.compareTo("201001")<0||yearmonth.compareTo("209912")>0)
		{
			message="文件名不符合规范";
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
				mm = sheet.getColumns();
				PFlagDAO fdao = new PFlagDAO();
				PFlag flag = fdao.findByIsNew(1);
				for (int i = 1; i < nn; i++) {
					PScore ps = new PScore();
					String name = sheet.getCell(1, i).getContents().trim();
					String newnumber = sheet.getCell(0, i).getContents().trim();
					if(newnumber==null||newnumber.equals("")||newnumber.isEmpty())
					{
//						message="导入的新一代编号为空";
//						return "failed";
					}
					else
					{
						for(int j=2;j<mm;j++)
						{
							if(sheet.getCell(j, 0).getContents().trim()==null||sheet.getCell(j, 0).getContents().trim().equals(""))
							{
								
							}
							else
							{
								String kciname = sheet.getCell(j, 0).getContents().trim();
								PKCIScore pkci = pkcdao.findByYearSeasonNewnumberTiyanduijie(flag.getYear(),flag.getSeason(),newnumber);
								ps = psdao.findByNewnumberYearSeasonnew(newnumber, flag.getYear(), flag.getSeason());
								double prop = 1;
								double sum = 0;
								if(pkci==null)
								{
									message+="姓名["+name+"],加分项名称["+kciname+"]在考核项目中查询不到，";
								}
								else
								{
									double score = 0.0;
									if(sheet.getCell(j, i).getContents()==null||sheet.getCell(j, i).getContents().trim().equals(""))
									{
										score = 0.0;
									}
									else
									{
										score = Double.parseDouble(sheet.getCell(j, i).getContents().trim());
									}
									if(pkci.getProp()!=null)
									{
										prop = pkci.getProp();
									}
									sum = score*prop;
									pkci.setResult1(sum);
									pkci.setSum(sum);
									pkcdao.merge(pkci);
//									ps.setKpiscore(sum);
//								    psdao.merge(ps);
								}
							}
						}
		    	      }
					
					}
				flag.setAlreadyrate(flag.getAlreadyrate()+"88888889");
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
}
