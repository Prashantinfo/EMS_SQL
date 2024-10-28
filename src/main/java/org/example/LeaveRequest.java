// LeaveRequest.java
package org.example;

import java.util.Date;

public class LeaveRequest {
    private String id;
    private int employeeId;
    private String leaveType;
    private Date startDate;
    private Date endDate;
    private int numberOfDays;
    private String reason;
    private String status; // PENDING, APPROVED, REJECTED
    private String approverComments;
    private Date applicationDate;

    // Constructor
    public LeaveRequest(int employeeId, String leaveType, Date startDate, Date endDate,
                        String reason) {
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = "PENDING";
        this.applicationDate = new Date();
        this.numberOfDays = calculateNumberOfDays();
    }

    // Add getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }
    public String getLeaveType() { return leaveType; }
    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getApproverComments() { return approverComments; }
    public void setApproverComments(String approverComments) { this.approverComments = approverComments; }
    public Date getApplicationDate() { return applicationDate; }
    public int getNumberOfDays() { return numberOfDays; }

    private int calculateNumberOfDays() {
        long diff = endDate.getTime() - startDate.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24)) + 1;
    }
}

