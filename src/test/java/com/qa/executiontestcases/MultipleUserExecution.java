package com.qa.executiontestcases;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.Test;

import com.excelutils.ExcelUtils;
import com.ey.iiqcommons.elementpage.AccessRequestElements;
import com.ey.iiqcommons.elementpage.LoginElements;
import com.ey.iiqcommons.elementpage.ManageUserAccessElements;
import com.ey.iiqcommons.elementpage.PerformIdentityRequestMaintenance;
import com.ey.iiqcommons.elementpage.TaskRun;
import com.ey.iiqcommons.elementpage.UserAccessValidation;
import com.ey.iiqcommons.elementpage.WorkItemsElements;
import com.qa.testbase.TestBase;

public class MultipleUserExecution extends TestBase {

	public LoginElements loginElements = null;
	public ManageUserAccessElements manageuserAccessElements = null;
	public AccessRequestElements accessRequestElements = null;
	public WorkItemsElements workitemElements = null;
	public TaskRun taskRun = null;
	public UserAccessValidation userAccessValidation = null;
	public PerformIdentityRequestMaintenance performIdentityMaintenance = null;

	public MultipleUserExecution() {
		super();

	}

	public String getConnectorType() throws IOException {
		String connectorType = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 2).trim();

		return connectorType;
	}

	// testcaseType
	public String getTestcaseType() throws IOException {
		String testcaseType = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 3).trim();

		return testcaseType;
	}

	// loginUserName
	public String getUserName() throws IOException {
		String userName = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 4).trim();

		return userName;
	}

	// loginPassword
	public String getPassword() throws IOException {
		String password = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 5).trim();
		return password;
	}

	// applicationName
	public String getApplicationName() throws IOException {
		String applicationName = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 6).trim();
		return applicationName;
	}

	// RequesteeName
	public String getRequestee() throws IOException {
		String requstee = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 7).trim();
		System.out.println("requstee : " + requstee);
		return requstee;
	}

	// EntitlementName
	public String getEntitlements() throws IOException {
		String entitlements = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 8).trim();
		System.out.println("entitlements : " + entitlements);
		return entitlements;
	}

	// request Type
	public String getRequestType() throws IOException {
		String requestType = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 9).trim();
		System.out.println("requestType : " + requestType);
		return requestType;
	}

	// request ID
	/*
	 * public String getRequestID() throws IOException { Integer request =
	 * ExcelUtils.getCellNumericData(excelsheetPath, xlsheet, 1, 10); String
	 * requestID = String.valueOf(request); System.out.println("requestID : " +
	 * requestID); return requestID; }
	 */

	public String getRequestID() throws IOException {
		String request = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 10).trim();
		String requestID = String.valueOf(request);
		System.out.println("requestID : " + requestID);
		return requestID;
	}

	// getCellNumericData

	// ManagerName
	public String getmanagerName() throws IOException {
		String ManagerName = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 11).trim();
		return ManagerName;
	}

	// ownerName
	public String getOwnerName() throws IOException {
		String OwnerName = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 12).trim();
		return OwnerName;
	}

	// decision
	public String getDecision() throws IOException {
		String decision = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 13).trim();
		return decision;
	}

	// PerformIdentityRequestMaintenance
	public String getPerformIdentityRequestMaintenance() throws IOException {

		String performIdentityRequestMaintenance = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 14).trim();
		return performIdentityRequestMaintenance;
	}

	// getAccountAggregation
	public String getAccountAggregation() throws IOException {

		String accountAggregation = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 15).trim();
		return accountAggregation;
	}

	// PerformMaintenance
	public String getPerformMaintenance() throws IOException {

		String PerformMaintenance = ExcelUtils.getCellData(excelsheetPath, xlsheet, 1, 16).trim();
		return PerformMaintenance;
	}

	// approval level count(Mgr/owmer or Owner)
	public int getApprovalLevel() throws IOException {

		Integer approvalLevel = ExcelUtils.getCellNumericData(excelsheetPath, xlsheet, 1, 17);

		return approvalLevel;
	}

	
	
	
	
	
	@Test(enabled = false)
	public void multipleUsersingleAccessExecution()
			throws IOException, InterruptedException, ElementNotVisibleException, AWTException {

		System.out.println("getApprovalLevel() : " + getApprovalLevel());

		int approvalLevelCount = getApprovalLevel();

		System.out.println("approvalLevelCount : " + approvalLevelCount);
		if (getConnectorType().equalsIgnoreCase("Webservices") || getConnectorType().equalsIgnoreCase("JDBC")
				|| getConnectorType().equalsIgnoreCase("SAP")) {

			loginElements = new LoginElements();
			loginElements.loginWithValidCredentials(getUserName(), getPassword());
			Thread.sleep(20000);

			manageuserAccessElements = new ManageUserAccessElements();
			manageuserAccessElements.multipleUserSingleAccess(getRequestee(),getApplicationName(), getEntitlements(), getRequestType(), report, test,
					driver);
 
			
			
			Thread.sleep(15000);

			accessRequestElements = new AccessRequestElements();
			accessRequestElements.accessRequestsForMultipleUser(getRequestee(), getEntitlements(), getApplicationName(),
					getRequestType(), report, test, driver);
			Thread.sleep(10000);

			workitemElements = new WorkItemsElements();

			if (approvalLevelCount > 1) {
				workitemElements.workItemForMultipleUser2levelApproval(getRequestID(), getTestcaseType(), getRequestType(), getmanagerName(), getOwnerName(),getDecision(), getEntitlements(),getRequestee());
			}

			else {

				workitemElements.workItemforMultipleUser1LevelApproval(getRequestID(), getTestcaseType(),
						getRequestType(), getmanagerName(),getOwnerName(), getDecision(), getEntitlements(), getRequestee());

			}
			Thread.sleep(10000);
			taskRun = new TaskRun();
			taskRun.taskRun(getPerformIdentityRequestMaintenance(), getAccountAggregation());

			Thread.sleep(10000);
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());

			userAccessValidation = new UserAccessValidation();
			Thread.sleep(10000);
			userAccessValidation.validateUserAccountsAndEntitlementForMultipleuser(getRequestee(), getRequestType(),
					getApplicationName(), getEntitlements());

		} else if (getConnectorType().equalsIgnoreCase("CSV")) {
			loginElements = new LoginElements();
			loginElements.loginWithValidCredentials(getUserName(), getPassword());
			Thread.sleep(20000);

			manageuserAccessElements = new ManageUserAccessElements();
			manageuserAccessElements.multipleUserSingleAccess(getRequestee(),getApplicationName(), getEntitlements(), getRequestType(), report, test,
					driver);
			Thread.sleep(15000);

			accessRequestElements = new AccessRequestElements();
			accessRequestElements.accessRequestsForMultipleUser(getRequestee(), getEntitlements(), getApplicationName(),
					getRequestType(), report, test, driver);
			Thread.sleep(10000);

			workitemElements = new WorkItemsElements();

			if (approvalLevelCount > 1) {
				workitemElements.workItemForMultipleUser2levelApproval(getRequestID(), getTestcaseType(), getRequestType(), getmanagerName(), getOwnerName(),getDecision(), getEntitlements(),getRequestee());
				Thread.sleep(5000);
				workitemElements.manualWorkActions(getOwnerName(), getEntitlements());
			}

			else {


				workitemElements.workItemforMultipleUser1LevelApproval(getRequestID(), getTestcaseType(),
						getRequestType(), getmanagerName(),getOwnerName(), getDecision(), getEntitlements(), getRequestee());
				Thread.sleep(5000);
				workitemElements.manualWorkActions(getOwnerName(), getEntitlements());
			}
			Thread.sleep(10000);
			taskRun = new TaskRun();
			taskRun.taskRun(getPerformIdentityRequestMaintenance(), getAccountAggregation());

			Thread.sleep(10000);
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());

			Thread.sleep(10000);
			userAccessValidation = new UserAccessValidation();

			userAccessValidation.validateUserAccountsAndEntitlementForMultipleuser(getRequestee(), getRequestType(),
					getApplicationName(), getEntitlements());

		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test(enabled = true)
	public void multipleUserMultipleAccessExecution()
			throws IOException, InterruptedException, ElementNotVisibleException, AWTException {

		System.out.println("getApprovalLevel() : " + getApprovalLevel());

		int approvalLevelCount = getApprovalLevel();

		System.out.println("approvalLevelCount : " + approvalLevelCount);
		if (getConnectorType().equalsIgnoreCase("Webservices") || getConnectorType().equalsIgnoreCase("JDBC")
				|| getConnectorType().equalsIgnoreCase("SAP")) {

			loginElements = new LoginElements();
			loginElements.loginWithValidCredentials(getUserName(), getPassword());
			Thread.sleep(20000);

		manageuserAccessElements = new ManageUserAccessElements();
			manageuserAccessElements.multipleUserMultipleAccess(getRequestee(),getApplicationName(),getEntitlements(),getRequestType(), report, test,
					driver);

			
			
			Thread.sleep(10000);

			accessRequestElements = new AccessRequestElements();
			accessRequestElements.accessRequestsForMultipleUserMultipleAccess(getRequestee(), getEntitlements(), getApplicationName(),
					getRequestType(), report, test, driver);
			Thread.sleep(10000);

	      	workitemElements = new WorkItemsElements();

			if (approvalLevelCount > 1) {
				workitemElements.workItemForMultipleUser2levelApproval(getRequestID(), getTestcaseType(), getRequestType(), getmanagerName(), getOwnerName(),getDecision(), getEntitlements(),getRequestee());
			}

			else {

				workitemElements.workItemforMultipleUser1LevelApproval(getRequestID(), getTestcaseType(),
						getRequestType(), getmanagerName(),getOwnerName(), getDecision(), getEntitlements(), getRequestee());

			}
			/*		Thread.sleep(10000);
			taskRun = new TaskRun();
			taskRun.taskRun(getPerformIdentityRequestMaintenance(), getAccountAggregation());

			Thread.sleep(10000);
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());

			userAccessValidation = new UserAccessValidation();
			Thread.sleep(10000);
			userAccessValidation.validateUserAccountsAndEntitlementForMultipleuser(getRequestee(), getRequestType(),
					getApplicationName(), getEntitlements());*/

		} /*else if (getConnectorType().equalsIgnoreCase("CSV")) {
			loginElements = new LoginElements();
			loginElements.loginWithValidCredentials(getUserName(), getPassword());
			Thread.sleep(20000);

			manageuserAccessElements = new ManageUserAccessElements();
			manageuserAccessElements.multipleUserMultipleAccess(getRequestee(),getApplicationName(),getEntitlements(),getRequestType(), report, test,
					driver);

			Thread.sleep(15000);

			accessRequestElements = new AccessRequestElements();
			accessRequestElements.accessRequestsForMultipleUser(getRequestee(), getEntitlements(), getApplicationName(),
					getRequestType(), report, test, driver);
			Thread.sleep(10000);

			workitemElements = new WorkItemsElements();

			if (approvalLevelCount > 1) {
				workitemElements.workItemForMultipleUser2levelApproval(getRequestID(), getTestcaseType(), getRequestType(), getmanagerName(), getOwnerName(),getDecision(), getEntitlements(),getRequestee());
				Thread.sleep(5000);
				workitemElements.manualWorkActions(getOwnerName(), getEntitlements());
			}

			else {


				workitemElements.workItemforMultipleUser1LevelApproval(getRequestID(), getTestcaseType(),
						getRequestType(), getmanagerName(),getOwnerName(), getDecision(), getEntitlements(), getRequestee());
				Thread.sleep(5000);
				workitemElements.manualWorkActions(getOwnerName(), getEntitlements());
			}
			Thread.sleep(10000);
			taskRun = new TaskRun();
			taskRun.taskRun(getPerformIdentityRequestMaintenance(), getAccountAggregation());

			Thread.sleep(10000);
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());

			Thread.sleep(10000);
			userAccessValidation = new UserAccessValidation();

			userAccessValidation.validateUserAccountsAndEntitlementForMultipleuser(getRequestee(), getRequestType(),
					getApplicationName(), getEntitlements());

		
		}
	*/
	
	
	
	}
	
	
	@Test(alwaysRun = true, enabled = true)
	public void multipleUserSingleAccessAutoApproveExecution() throws InterruptedException, IOException, AWTException {

   if (getConnectorType().equalsIgnoreCase("Webservices")||getConnectorType().equalsIgnoreCase("JDBC")||getConnectorType().equalsIgnoreCase("SAP")) {
		
		loginElements = new LoginElements();
		loginElements.loginWithValidCredentials(getUserName(), getPassword());
		Thread.sleep(3000);
	    manageuserAccessElements = new ManageUserAccessElements();
		manageuserAccessElements.SingleUserMultipleAccessAutoApproval(getRequestee(),getApplicationName(),getEntitlements(), getRequestType(), report, test,
				driver);
		Thread.sleep(10000);
		accessRequestElements = new AccessRequestElements();
		accessRequestElements.accessRequestsForMultipleUser(getRequestee(), getEntitlements(), getApplicationName(),
				getRequestType(), report, test, driver);
		Thread.sleep(10000);
		
		
		Thread.sleep(10000);
	    TaskRun run = new TaskRun();
		run.taskRun_PerformMaintenance(getPerformIdentityRequestMaintenance());
		Thread.sleep(8000);



		System.out.println("singleUserSingleAccessAddEnttritlement : task run done");

		accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
		System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");
		Thread.sleep(5000);

		userAccessValidation = new UserAccessValidation();
		userAccessValidation.validateUserAccountsAndEntitlements(getRequestee(), getRequestType(), getApplicationName(),
				getEntitlements());
		System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");

		
			
}

   
	else if (getConnectorType().equalsIgnoreCase("CSV")) {
		loginElements = new LoginElements();
		loginElements.loginWithValidCredentials(getUserName(), getPassword());
		Thread.sleep(3000);
	    manageuserAccessElements = new ManageUserAccessElements();
	    manageuserAccessElements.SingleUserMultipleAccessAutoApproval(getRequestee(),getApplicationName(),getEntitlements(), getRequestType(), report, test,
				driver);
		Thread.sleep(10000);
		accessRequestElements = new AccessRequestElements();
		accessRequestElements.accessRequestsForMultipleUser(getRequestee(), getEntitlements(), getApplicationName(),
				getRequestType(), report, test, driver);
		Thread.sleep(10000);
	
	TaskRun run = new TaskRun();
	run.taskRun_PerformMaintenance(getPerformIdentityRequestMaintenance());
		Thread.sleep(8000);

	   Thread.sleep(3000);

		System.out.println("singleUserSingleAccessAddEnttritlement : task run done");

		accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
		System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");
		Thread.sleep(5000);

		userAccessValidation = new UserAccessValidation();
		userAccessValidation.validateUserAccountsAndEntitlements(getRequestee(), getRequestType(), getApplicationName(),
				getEntitlements());
		System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");

		
			
	}
   	}

	
	
	

}
