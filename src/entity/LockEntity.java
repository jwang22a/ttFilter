package entity;
/**
 * �쳣�˺żӽ���ʵ����
 * @author Administrator
 *
 */
public class LockEntity {
	    public String applyTitle;     //��������;
		public String systemName;     //ҵ��ϵͳ����;
		public String systemAccount;  //ҵ��ϵͳ�˺�;
		public String accountGroup;   //�˺�������֯ ;
		public String dealingPeople;  //����������;
		public String userId;         //�����˹��� ;
		public String operatoring;    //����;
		public String lockReason;     //ԭ�� ;
		public String getApplyTitle() {
			return applyTitle;
		}
		public void setApplyTitle(String applyTitle) {
			this.applyTitle = applyTitle;
		}
		public String getSystemName() {
			return systemName;
		}
		public void setSystemName(String systemName) {
			this.systemName = systemName;
		}
		public String getSystemAccount() {
			return systemAccount;
		}
		public void setSystemAccount(String systemAccount) {
			this.systemAccount = systemAccount;
		}
		public String getAccountGroup() {
			return accountGroup;
		}
		public void setAccountGroup(String accountGroup) {
			this.accountGroup = accountGroup;
		}
		public String getDealingPeople() {
			return dealingPeople;
		}
		public void setDealingPeople(String dealingPeople) {
			this.dealingPeople = dealingPeople;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getOperatoring() {
			return operatoring;
		}
		public void setOperatoring(String operatoring) {
			this.operatoring = operatoring;
		}
		public String getLockReason() {
			return lockReason;
		}
		public void setLockReason(String lockReason) {
			this.lockReason = lockReason;
		}
		
	
}
