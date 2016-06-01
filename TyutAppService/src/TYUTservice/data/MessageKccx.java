package TYUTservice.data;

import java.util.HashMap;
import java.util.List;

import TYUTservice.data.msgdata.Course;
import TYUTservice.data.msgdata.KccxMsg;

public class MessageKccx extends MessageTyut {
	private HashMap<String, HashMap<String, Course>> kccxMsgs;

	public HashMap<String, HashMap<String, Course>> getKccxMsgs() {
		return kccxMsgs;
	}

	public void setKccxMsgs(HashMap<String, HashMap<String, Course>> kccxMsgs) {
		this.kccxMsgs = kccxMsgs;
	}

	public MessageKccx() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageKccx(int id, int status,HashMap<String, HashMap<String, Course>> kccxMsgs) {
		super(id, status);
		this.kccxMsgs=kccxMsgs;
		// TODO Auto-generated constructor stub
	}
}
