import java.util.ArrayList;
import java.util.Scanner;


public class isearch {

	/**
	 * @param args
	 */
	static int level =0;
	static int TOTAL_COST =0;
	static String statesReached= new String();
	public static void main(String[] args) {
		
		String src = new String("ASAABBB");//also test with SAAABBB, result comes as (BSBBAAA Goal state   weight of optimal path 12)
		
		System.out.println("Start state: +" + src + " \nPRESS 1 for heuristic 1, 2 for using heuristic 2 \n");
		
		Scanner in2 = new Scanner(System.in);
		
		int M=in2.nextInt();
		if( M == 1){
		findall1(src,0);//find all states using heuristic 1
		}
		else if( M == 2){
			findall2(src,0);//find all states using heuristic 2
		}
		else if( M == 3){
			findall3(src,0);//find all states using heuristic 3
		}
		
		

	}

	
	/*
	 * heuristic one checks the distance of the current state to the goal state and takes the optimum one  heuristic_val+cost.
	 * The cost is cost of moving the tile
	 * heuristic_val is calculated in  check_heuristic2 based on the 7 goal states.
	 * After each level a recursive call is made to find the next optimum cost to the goal state.
	 */
	private static void findall2(String src,int weight_propogate) {
		String tmp =src;int i=0;
		
		statesReached.concat("#" +src+"#");
		System.out.println(" string tested at level " + level + ":  " +src + " weight propogated "+weight_propogate);
		int k=0;
		int cost=0;
		int opt_weight=0;
		String opt_path_string=null;
		
		Boolean chkstate=false;
		while(i < 7 ){
			String strtmp;
			int space=src.indexOf('S');
			if(i!=space){
				//swap	
				char[] c = src.toCharArray();
				char temp = c[i];
				c[i] = c[space];
				c[space] = temp;

				tmp = new String(c);
				cost = space-i>0?space-i:i-space;
				cost= cost==1?cost:cost-1;
				

				k++;
				chkstate= checkGoal(tmp);
				int heuristic_val = check_heuristic2(tmp,src);
				int weight=heuristic_val+cost;
				

				if(opt_weight==0){

					opt_weight=weight;
					opt_path_string=tmp;
				}
					

				if(opt_weight>=weight){
					opt_weight=weight;
					opt_path_string=tmp;
				}
				if(chkstate==true){

					opt_weight=weight;
					opt_path_string=tmp;
					break;
					
				}
		
			}
			
			i++;
		}
		level++;
		//if(chkstate==true || level >10){
		if(chkstate==true){
			System.out.println(" string tested at level "  + level + ":  " +opt_path_string +" Goal state "+ "  weight of optimal path " + TOTAL_COST);
			return;
		}
		else{
			TOTAL_COST+=cost;
			findall1(opt_path_string,cost);
		}
		
		System.out.println("");
		
	}
	/*
	 * seven goal states are there as shown. heuristic for each is calculated and returned.
	 */
	private static int check_heuristic2(String tmp, String src) {
		
		//check no of misplaced tiles
		//compare the tmp state/string to each pf the 7 goal staes and which ever is maximum return that
		String goal1 ="BBBAAAS";
		String goal2="SBBBAAA";
		String goal3="BBBSAAA";
		String goal4="BBBASAA";
		String goal5="BBBAASA";
		String goal6="BBSBAAA";
		String goal7="BSBBAAA";
		ArrayList GOALstates = new ArrayList();
		GOALstates.add(goal1);
		GOALstates.add(goal2);
		GOALstates.add(goal3);
		GOALstates.add(goal4);
		GOALstates.add(goal5);
		GOALstates.add(goal6);
		GOALstates.add(goal7);
		int i =0; int k=0;
			
		
		int heuristic_all[]= new int[7];
		while(i<7){
			String goal = (String) GOALstates.get(i);
			int no_misplaced_tile=0;
			for(int k3=0;k3<7;k3++){
				if(goal.charAt(k3)==tmp.charAt(k3)){
					//System.out.println("char eqls");
				}else{
					//System.out.println("char not eqls");
					no_misplaced_tile++;
				}
					
			}
			heuristic_all[i]=no_misplaced_tile;
			i++;
		}
		
		int max = heuristic_all[0];
		for(int k1=0;k1<7;k1++){
			if(max<heuristic_all[k1])
				max = heuristic_all[k1];
		}
			
		return max;//return min value
		
	}

	/*
	 * heuristic one checks the distance of the current state to the goal state and takes the optimum one  heuristic_val+cost.
	 * The cost is cost of moving the tile
	 * heuristic_val is calculated in  check_heuristic1 based on the 7 goal states.
	 * After each level a recursive call is made to find the next optimum cost to the goal state.
	 */
	private static void findall1(String src,int weight_propogate) {
		String tmp =src;int i=0;
		//System.out.println(" string tested at level " + level + ":  " +src);
		statesReached.concat("#" +src+"#");
		System.out.println(" string tested at level " + level + ":  " +src + " weight propogated "+weight_propogate);
		int k=0;
		int cost=0;
		int opt_weight=0;
		String opt_path_string=null;
		
		Boolean chkstate=false;
		while(i < 7 ){
			String strtmp;
			int space=src.indexOf('S');
			if(i!=space){
			
				char[] c = src.toCharArray();

				//swap
				char temp = c[i];
				c[i] = c[space];
				c[space] = temp;

				tmp = new String(c);
				cost = space-i>0?space-i:i-space;
				cost= cost==1?cost:cost-1;
				k++;
				chkstate= checkGoal(tmp);
				int heuristic_val = check_heuristic1(tmp,src);
				int weight=heuristic_val+cost;
				
				if(opt_weight==0){
					opt_weight=weight;
					opt_path_string=tmp;
				}
					
				
				if(opt_weight>=weight){
					opt_weight=weight;
					opt_path_string=tmp;
				}
				if(chkstate==true){
					opt_weight=weight;
					opt_path_string=tmp;
					break;
					
				}
		
			}
			
			i++;
		}
		level++;
		//if(chkstate==true || level >10){
		if(chkstate==true){
			
			System.out.println(" string tested at level "  + level + ":  " +opt_path_string +" Goal state "+ "  weight of optimal path " + TOTAL_COST);
			return;
		}
		else{
			TOTAL_COST+=cost;
			findall1(opt_path_string,cost);
		}
		
		System.out.println("");
		
	}
	
	
	private static void findall3(String src, int weight_propogate) {

		String tmp =src;int i=0;
		statesReached.concat("#" +src+"#");
		System.out.println(" string tested at level " + level + ":  " +src + " weight propogated "+weight_propogate);
		int k=0;
		int cost=0;
		int opt_weight=0;
		String opt_path_string=null;
		
		Boolean chkstate=false;
		while(i < 7 ){
			String strtmp;
			int space=src.indexOf('S');
			if(i!=space){
			
				char[] c = src.toCharArray();

				//swap
				char temp = c[i];
				c[i] = c[space];
				c[space] = temp;

				tmp = new String(c);
				cost = space-i>0?space-i:i-space;
				cost= cost==1?cost:cost-1;
				
				//cost=cost+weight_propogate;
				k++;
				chkstate= checkGoal(tmp);
				int heuristic_val1 = check_heuristic1(tmp,src);
				int heuristic_val2 = check_heuristic2(tmp,src);
				int heuristic_val = heuristic_val1>heuristic_val2?heuristic_val1:heuristic_val2;
				int weight=heuristic_val+cost;
				
				//opt_weight=weight;
				if(opt_weight==0){
					//cost_propogated=cost;
					opt_weight=weight;
					opt_path_string=tmp;
				}
					
				//if(opt_weight>=weight && statesReached.indexOf("#" +tmp+"#")!=-1){
				if(opt_weight>=weight){
					//cost_propogated=cost;
					//cost_propogated=cost;
					opt_weight=weight;
					opt_path_string=tmp;
				}
				if(chkstate==true){
					//cost_propogated=cost;
					opt_weight=weight;
					opt_path_string=tmp;
					break;
					
				}
		
			}
			
			i++;
		}
		level++;
		//if(chkstate==true || level >10){
		if(chkstate==true){
			
			System.out.println(" string tested at level "  + level + ":  " +opt_path_string +" Goal state "+ "  weight of optimal path " + TOTAL_COST);
			return;
		}
		else{
			TOTAL_COST+=cost;
			findall1(opt_path_string,cost);
		}
		
		System.out.println("");
	
		
	}


	/*
	 * seven gola states are there as shown. heuristic for each is calculated and returned.
	 */
	private static int check_heuristic1(String tmp, String src) {
		
		String goal ="BBBAAAS";
		String goal2="SBBBAAA";
		String goal3="BBBSAAA";
		String goal4="BBBASAA";
		String goal5="BBBAASA";
		String goal6="BBSBAAA";
		String goal7="BSBBAAA";
		int i =1; int k=0;
		int aindex[]= new int[4];
		int bindex[]= new int[4];
		int gaindex[]= new int[4];
		int gbindex[]= new int[4];
		int ga2index[]= new int[4];
		int gb2index[]= new int[4];
		int ga3index[]= new int[4];
		int gb3index[]= new int[4];
		int ga4index[]= new int[4];
		int gb4index[]= new int[4];
		int ga5index[]= new int[4];
		int gb5index[]= new int[4];
		int ga6index[]= new int[4];
		int gb6index[]= new int[4];
		int ga7index[]= new int[4];
		int gb7index[]= new int[4];
		
		for(int k1=0;k1<4;k1++){
			aindex[k1]= 0;
			bindex[k1]= 0;
			gaindex[k1]= 0;
			gbindex[k1]=0;
			ga2index[k1]= 0;
			gb2index[k1]=0;
			ga3index[k1]= 0;
			gb3index[k1]=0;
			ga4index[k1]= 0;
			gb4index[k1]=0;
			ga5index[k1]= 0;
			gb5index[k1]=0;
			ga6index[k1]= 0;
			gb6index[k1]=0;
			ga7index[k1]= 0;
			gb7index[k1]=0;
		}
			
		while(i<4){
			aindex[i]=tmp.substring(aindex[i-1]).indexOf('A') + aindex[i-1] + 1;
			bindex[i]=tmp.substring(bindex[i-1]).indexOf('B') + bindex[i-1] + 1;
			gaindex[i]=goal.substring(gaindex[i-1]).indexOf('A') + gaindex[i-1] + 1;
			gbindex[i]=goal.substring(gbindex[i-1]).indexOf('B') + gbindex[i-1] + 1;
			
			ga2index[i]=goal2.substring(ga2index[i-1]).indexOf('A') + ga2index[i-1] + 1;
			gb2index[i]=goal2.substring(gb2index[i-1]).indexOf('B') + gb2index[i-1] + 1;
			
			ga3index[i]=goal3.substring(ga3index[i-1]).indexOf('A') + ga3index[i-1] + 1;
			gb3index[i]=goal3.substring(gb3index[i-1]).indexOf('B') + gb3index[i-1] + 1;
			
			ga4index[i]=goal4.substring(ga4index[i-1]).indexOf('A') + ga4index[i-1] + 1;
			gb4index[i]=goal4.substring(gb4index[i-1]).indexOf('B') + gb4index[i-1] + 1;
			
			ga5index[i]=goal5.substring(ga5index[i-1]).indexOf('A') + ga5index[i-1] + 1;
			gb5index[i]=goal5.substring(gb5index[i-1]).indexOf('B') + gb5index[i-1] + 1;
			
			ga6index[i]=goal6.substring(ga6index[i-1]).indexOf('A') + ga6index[i-1] + 1;
			gb6index[i]=goal6.substring(gb6index[i-1]).indexOf('B') + gb6index[i-1] + 1;
			
			ga7index[i]=goal7.substring(ga7index[i-1]).indexOf('A') + ga7index[i-1] + 1;
			gb7index[i]=goal7.substring(gb7index[i-1]).indexOf('B') + gb7index[i-1] + 1;
			
			//System.out.println("h values "+tmp + aindex[i]+ bindex[i]+goal+gaindex[i] +gbindex[i]+ goal2+ ga2index[i]+ gb2index[i] + goal3+ga3index[i]+gb3index[i]);
			//System.out.println("h values " + aindex[i] + tmp.substring(aindex[i-1]));
			i++;
			
		}
		int sum=0;
		int sumg2=0;
		int sumg3=0;
		int sumg4=0;
		int sumg5=0;
		int sumg6=0;
		int sumg7=0;
		int heuristic_all[] = new int[7];
		for(int k2=1;k2<4;k2++){
			int sum1=gaindex[k2]-aindex[k2]>0?gaindex[k2]-aindex[k2]:0;
			int sum2=bindex[k2]-gbindex[k2]>0?bindex[k2]-gbindex[k2]:0;
			sum+=sum1+sum2;
			heuristic_all[0]=sum;
			
			int sum1g2=ga2index[k2]-aindex[k2]>0?ga2index[k2]-aindex[k2]:0;
			int sum2g2=bindex[k2]-gb2index[k2]>0?bindex[k2]-gb2index[k2]:0;
			sumg2+=sum2g2+sum1g2;
			heuristic_all[1]=sumg2;
			
			int sum1g3=ga3index[k2]-aindex[k2]>0?ga3index[k2]-aindex[k2]:0;
			int sum2g3=bindex[k2]-gb3index[k2]>0?bindex[k2]-gb3index[k2]:0;
			sumg3+=sum1g3+sum2g3;
			heuristic_all[2]=sumg3;
			
			int sum1g4=ga4index[k2]-aindex[k2]>0?ga4index[k2]-aindex[k2]:0;
			int sum2g4=bindex[k2]-gb4index[k2]>0?bindex[k2]-gb4index[k2]:0;
			sumg4+=sum2g4+sum1g4;
			heuristic_all[3]=sumg4;
			
			int sum1g5=ga5index[k2]-aindex[k2]>0?ga5index[k2]-aindex[k2]:0;
			int sum2g5=bindex[k2]-gb5index[k2]>0?bindex[k2]-gb5index[k2]:0;
			sumg5+=sum1g5+sum2g5;
			heuristic_all[4]=sumg5;
			
			int sum1g6=ga6index[k2]-aindex[k2]>0?ga6index[k2]-aindex[k2]:0;
			int sum2g6=bindex[k2]-gb6index[k2]>0?bindex[k2]-gb6index[k2]:0;
			sumg6+=sum2g6+sum1g6;
			heuristic_all[5]=sumg6;
			
			int sum1g7=ga7index[k2]-aindex[k2]>0?ga7index[k2]-aindex[k2]:0;
			int sum2g7=bindex[k2]-gb7index[k2]>0?bindex[k2]-gb7index[k2]:0;
			sumg7+=sum1g7+sum2g7;
			heuristic_all[6]=sumg7;
		}
		int max = heuristic_all[0];
		for(int k1=0;k1<7;k1++){
			if(max<heuristic_all[k1])
				max = heuristic_all[k1];
		}
			
		return max;
		
	}

	/*
	 * check goal state
	 * input parameter: state of string generated at each step
	 * return true of tmp is a goal state else false
	 */
	private static Boolean checkGoal(String tmp) {
		int last2index=tmp.lastIndexOf('B');
		for(int k=0;k<last2index;k++){
			String subtr= tmp.substring(0, k);
			if(subtr.indexOf('A')!=-1)
				return false;
		}
		return true;
	}

}
