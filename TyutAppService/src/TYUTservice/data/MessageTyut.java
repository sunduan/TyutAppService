package TYUTservice.data;

public class MessageTyut {
	public MessageTyut(){
		
	}
	public MessageTyut(int id, int status) {
		super();
		this.id = id;
		this.status = status;
	}

	// ��Ϣid 1.��¼,2.��ȡ�γ̱�....�ȵ�
	private int id;
	// ��Ϣ��ȡ״̬1.ʧ��1��2ʧ��2��3�ɹ���������
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
