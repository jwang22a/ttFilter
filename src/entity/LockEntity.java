package entity;
/**
 * 异常账号加解锁实体类
 * @author Administrator
 *
 */
public class LockEntity {
	    public String applyTitle;     //工单标题;
		public String systemName;     //业务系统名称;
		public String systemAccount;  //业务系统账号;
		public String accountGroup;   //账号所属组织 ;
		public String dealingPeople;  //处理人姓名;
		public String userId;         //处理人工号 ;
		public String operatoring;    //操作;
		public String lockReason;     //原因 ;
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
