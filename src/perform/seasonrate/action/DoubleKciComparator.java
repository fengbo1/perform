package perform.seasonrate.action;
import java.util.Comparator;

import perform.seasonrate.bean.SeasonScoreBean2;
public class DoubleKciComparator implements Comparator<SeasonScoreBean2> {
	public int compare(SeasonScoreBean2 s1, SeasonScoreBean2 s2) {
		// TODO Auto-generated method stub
		if(Double.valueOf(s1.getKci())>Double.valueOf(s2.getKci()))
		{
			return -1;
		}
		if(Double.valueOf(s1.getKci())<Double.valueOf(s2.getKci()))
		{
			return 1;
		}
		return 0;
		
	}
}
