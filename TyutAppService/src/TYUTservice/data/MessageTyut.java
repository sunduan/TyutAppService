package TYUTservice.data;

public class MessageTyut {
	public MessageTyut(){
		
	}
	public MessageTyut(int id, int status) {
		super();
		this.id = id;
		this.status = status;
	}

	// id�� 1.��¼,2.�γ̲�ѯ,3.�����ɼ�,4.��������,5.���Թ���,6.��ѧ����,7.���˹���....�ȵ�
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
