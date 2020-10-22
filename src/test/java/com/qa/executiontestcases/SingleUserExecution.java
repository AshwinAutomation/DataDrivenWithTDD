package com.qa.executiontestcases;

import java.awt.AWTException;
import java.io.IOException;
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

public class SingleUserExecution extends TestBase {
	public LoginElements loginElements = null;
	public ManageUserAccessElements manageuserAccessElements = null;
	public AccessRequestElements accessRequestElements = null;
	public WorkItemsElements workitemElements = null;
	public TaskRun run = null;
	public UserAccessValidation userAccessValidation = null;
	public PerformIdentityRequestMaintenance performIdentityMaintenance = null;

	public SingleUserExecution() {
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

	// Testcase Execution

	@Test(enabled = false)
	public void singleUserSingleAccessExecution() throws InterruptedException, IOException, AWTException {

		System.out.println("getApprovalLevel() : " + getApprovalLevel());
		System.out.println("testcasetype : " + getTestcaseType());
		int approvalLevelCount = getApprovalLevel();

		System.out.println("approvalLevelCount : " + approvalLevelCount);

		if (getConnectorType().equalsIgnoreCase("Webservices") || getConnectorType().equalsIgnoreCase("JDBC")
				|| getConnectorType().equalsIgnoreCase("SAP")) {

			loginElements = new LoginElements();
			loginElements.loginWithValidCredentials(getUserName(), getPassword());
			Thread.sleep(10000);
			manageuserAccessElements = new ManageUserAccessElements();
			manageuserAccessElements.SingleUserSingleAccess(getRequestee(),getApplicationName(),getEntitlements(), getRequestType(), report, test,
					driver);
			
       	Thread.sleep(10000);
			accessRequestElements = new AccessRequestElements();
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
			Thread.sleep(10000);

			workitemElements = new WorkItemsElements();

			if (approvalLevelCount > 1) {

				workitemElements.workItemManagerAndOwnerApproval(getRequestID(), getTestcaseType(), getRequestType(),
						getmanagerName(), getOwnerName(), getDecision(), getEntitlements());

			} else {
				workitemElements.workItem1LevelApprovalForSingleUser(getRequestID(), getTestcaseType(), getRequestType(),
						getOwnerName(), getDecision(), getEntitlements(), getRequestee());
			}

		Thread.sleep(10000);
			TaskRun run = new TaskRun();
			//run.taskRun_PerformMaintenance(getPerformIdentityRequestMaintenance());
			
			run.taskRun(getPerformIdentityRequestMaintenance(), getAccountAggregation());
			
			System.out.println("singleUserSingleAccessAddEnttritlement : task run done");
			
			Thread.sleep(10000);

		/*	
			 performIdentityMaintenance = new PerformIdentityRequestMaintenance();
			 
			 performIdentityMaintenance.runPerformIdentityRequestMaintenance(
			 getPerformIdentityRequestMaintenance()); Thread.sleep(3000);*/
			 
			System.out.println("singleUserSingleAccessAddEnttritlement : task run done");
			accessRequestElements = new AccessRequestElements();
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
			System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");
			Thread.sleep(10000);

			userAccessValidation = new UserAccessValidation();
			userAccessValidation.validateUserAccountsAndEntitlements(getRequestee(), getRequestType(),
					getApplicationName(), getEntitlements());
			System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");

		}

		else if (getConnectorType().equalsIgnoreCase("CSV")) {
			loginElements = new LoginElements();
			loginElements.loginWithValidCredentials(getUserName(), getPassword());
			Thread.sleep(3000);
			manageuserAccessElements = new ManageUserAccessElements();
			manageuserAccessElements.SingleUserSingleAccess(getRequestee(),getApplicationName(),getEntitlements(), getRequestType(), report, test,
					driver);
			Thread.sleep(10000);
			accessRequestElements = new AccessRequestElements();
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
			Thread.sleep(10000);

			workitemElements = new WorkItemsElements();

			if (approvalLevelCount > 1) {

				workitemElements.workItemManagerAndOwnerApproval(getRequestID(), getTestcaseType(), getRequestType(),
						getmanagerName(), getOwnerName(), getDecision(), getEntitlements());
				Thread.sleep(5000);
				workitemElements.manualWorkActions(getOwnerName(), getEntitlements());

			} else {
				workitemElements.workItem1LevelApprovalForSingleUser(getRequestID(), getTestcaseType(), getRequestType(),
						getOwnerName(), getDecision(), getEntitlements(), getRequestee());
				Thread.sleep(5000);
				workitemElements.manualWorkActions(getOwnerName(), getEntitlements());
			}

			
			
		/*	
			
			Thread.sleep(10000);
			TaskRun run = new TaskRun();
			run.taskRun(getPerformIdentityRequestMaintenance(), getAccountAggregation());
			Thread.sleep(8000);
*/
			
			TaskRun run = new TaskRun();
			run.taskRun_PerformMaintenance(getPerformIdentityRequestMaintenance());
			System.out.println("singleUserSingleAccessAddEnttritlement : task run done");
			Thread.sleep(8000);
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
			System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");
			Thread.sleep(5000);

			userAccessValidation = new UserAccessValidation();
			userAccessValidation.validateUserAccountsAndEntitlements(getRequestee(), getRequestType(),
					getApplicationName(), getEntitlements());
			System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");

		}
	}

	@Test(enabled = true)
	public void singleUserMultipleAccessExecution() throws IOException, InterruptedException, AWTException {

		System.out.println(" inside singleUserMultipleAccessExecution");
		int approvalLevelCount = getApprovalLevel();

		System.out.println("approvalLevelCount : " + approvalLevelCount);

		if (getConnectorType().equalsIgnoreCase("Webservices") || getConnectorType().equalsIgnoreCase("JDBC")
				|| getConnectorType().equalsIgnoreCase("SAP")) {
			loginElements = new LoginElements();
			loginElements.loginWithValidCredentials(getUserName(), getPassword());
			Thread.sleep(3000);
			manageuserAccessElements = new ManageUserAccessElements();
			manageuserAccessElements.SingleUserMultipleAccess(getRequestee(),getApplicationName(),getEntitlements(), getRequestType(), report, test,
					driver);
			Thread.sleep(10000);
			accessRequestElements = new AccessRequestElements();
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
			Thread.sleep(10000);

			workitemElements = new WorkItemsElements();

			if (approvalLevelCount > 1) {

				workitemElements.workItemManagerAndOwnerApproval(getRequestID(), getTestcaseType(), getRequestType(),
						getmanagerName(), getOwnerName(), getDecision(), getEntitlements());

			} else {

				workitemElements.workItem1LevelApprovalForSingleUser(getRequestID(), getTestcaseType(), getRequestType(),
						getOwnerName(), getDecision(), getEntitlements(), getRequestee());
			}

		Thread.sleep(10000);
			TaskRun run = new TaskRun();
			run.taskRun(getPerformIdentityRequestMaintenance(), getAccountAggregation());
			Thread.sleep(8000);

/*			Thread.sleep(10000);
			TaskRun run = new TaskRun();
			run.taskRun_PerformMaintenance(getPerformIdentityRequestMaintenance());
			System.out.println("singleUserSingleAccessAddEnttritlement : task run done");*/
			Thread.sleep(8000);
			System.out.println("singleUserSingleAccessAddEnttritlement : task run done");

			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
			System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");
			Thread.sleep(5000);

			userAccessValidation = new UserAccessValidation();
			userAccessValidation.validateUserAccountsAndEntitlements(getRequestee(), getRequestType(),
					getApplicationName(), getEntitlements());
			System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");

		}

		else if (getConnectorType().equalsIgnoreCase("CSV")) {
			loginElements = new LoginElements();
			loginElements.loginWithValidCredentials(getUserName(), getPassword());
			Thread.sleep(3000);
			manageuserAccessElements = new ManageUserAccessElements();
			manageuserAccessElements.SingleUserMultipleAccess(getRequestee(),getEntitlements(),getEntitlements(), getRequestType(), report, test,
					driver);
			Thread.sleep(10000);
			accessRequestElements = new AccessRequestElements();
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
			Thread.sleep(10000);

			workitemElements = new WorkItemsElements();

			if (approvalLevelCount > 1) {

				workitemElements.workItemManagerAndOwnerApproval(getRequestID(), getTestcaseType(), getRequestType(),
						getmanagerName(), getOwnerName(), getDecision(), getEntitlements());
				Thread.sleep(5000);
				workitemElements.manualWorkActions(getOwnerName(), getEntitlements());

			} else {
				workitemElements.workItem1LevelApprovalForSingleUser(getRequestID(), getTestcaseType(), getRequestType(),
						getOwnerName(), getDecision(), getEntitlements(), getRequestee());
				Thread.sleep(5000);
				workitemElements.manualWorkActions(getOwnerName(), getEntitlements());
			}

			Thread.sleep(10000);
			TaskRun run = new TaskRun();
			run.taskRun(getPerformIdentityRequestMaintenance(), getAccountAggregation());
	

			/*
			 * performIdentityMaintenance = new PerformIdentityRequestMaintenance();
			 * 
			 * performIdentityMaintenance.runPerformIdentityRequestMaintenance(
			 * getPerformIdentityRequestMaintenance()); Thread.sleep(3000);
			 */

			System.out.println("singleUserMultipleAccessAddEnttritlement : task run done");
			Thread.sleep(8000);
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
			System.out.println("singleUserMultipleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");
			Thread.sleep(5000);

			userAccessValidation = new UserAccessValidation();
			userAccessValidation.validateUserAccountsAndEntitlements(getRequestee(), getRequestType(),
					getApplicationName(), getEntitlements());
			System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");

		}
	}

	@Test(enabled = false)
	public void singleUserSingleAccessAutoApprovedExecution() throws InterruptedException, IOException, AWTException {

		if (getConnectorType().equalsIgnoreCase("Webservices") || getConnectorType().equalsIgnoreCase("JDBC")
				|| getConnectorType().equalsIgnoreCase("SAP")) {

			loginElements = new LoginElements();
			loginElements.loginWithValidCredentials(getUserName(), getPassword());
			Thread.sleep(10000);
	     manageuserAccessElements = new ManageUserAccessElements();

         manageuserAccessElements.SingleUserSingleAccessAutoApproval(getRequestee(),getApplicationName(),getEntitlements(), getRequestType(), report, test,
		 driver);
			Thread.sleep(10000);
			accessRequestElements = new AccessRequestElements();
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
			Thread.sleep(20000);
			TaskRun run = new TaskRun();
			 run.taskRun_PerformMaintenance(getPerformIdentityRequestMaintenance());
			 Thread.sleep(10000);
			 


			System.out.println("singleUserSingleAccessAddEnttritlement : task run done");

			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
			System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");
			Thread.sleep(10000);

			userAccessValidation = new UserAccessValidation();
			userAccessValidation.validateUserAccountsAndEntitlements(getRequestee(), getRequestType(),
					getApplicationName(), getEntitlements());
			System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");

		}

		else if (getConnectorType().equalsIgnoreCase("CSV")) {
			loginElements = new LoginElements();
			loginElements.loginWithValidCredentials(getUserName(), getPassword());
			Thread.sleep(3000);
			manageuserAccessElements = new ManageUserAccessElements();
			  manageuserAccessElements.SingleUserSingleAccessAutoApproval(getRequestee(),getApplicationName(),getEntitlements(), getRequestType(), report, test,
						 driver);
			Thread.sleep(10000);
			accessRequestElements = new AccessRequestElements();
			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
			Thread.sleep(10000);

			Thread.sleep(10000);
			/*
			 * TaskRun run = new TaskRun();
			 * run.taskRun(getPerformIdentityRequestMaintenance(), getAccountAggregation());
			 * Thread.sleep(8000);
			 */

			performIdentityMaintenance = new PerformIdentityRequestMaintenance();

			performIdentityMaintenance.runPerformIdentityRequestMaintenance(getPerformIdentityRequestMaintenance());
			Thread.sleep(3000);

			System.out.println("singleUserSingleAccessAddEnttritlement : task run done");

			accessRequestElements.accessRequestsForSingleUserSingleAccess(getRequestID());
			System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");
			Thread.sleep(5000);

			userAccessValidation = new UserAccessValidation();
			userAccessValidation.validateUserAccountsAndEntitlements(getRequestee(), getRequestType(),
					getApplicationName(), getEntitlements());
			System.out.println("singleUserSingleAccessAddEnttritlement : accessRequestsForSingleUserSingleAccess done");

		}
	}

	
	
	

	
	
	
	
	
	
	
	
	
	
}
