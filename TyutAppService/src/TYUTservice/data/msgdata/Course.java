package TYUTservice.data.msgdata;

public class Course {
	  private String name;//�γ����ơ��Ͽν��ң���ʦ���γ̱��  
	    int start,step; //��ʼ�ϿνڴΣ� һ�����ڿ�  
	    public Course(String name,  int start, int step 
	          ) {  
	        super();   
	        this.name=name;
	        this.start = start;  
	        this.step = step;  

	    }  
	  

	  
	    public String getName() {  
	        return name;  
	    }  
	  
	    public void setName(String name) {  
	        this.name = name;  
	    }  

	  
	    public int getStart() {  
	        return start;  
	    }  
	  
	    public void setStart(int start) {  
	        this.start = start;  
	    }  
	  
	    public int getStep() {  
	        return step;  
	    }  
	  
	    public void setStep(int step) {  
	        this.step = step;  
	    }  
	  
	      
}
