package org.example;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaveManagementDAO {
    private final MongoCollection<Document> leaveCollection;
//    private final EmployeeDaoIntrf employeeDao;

    public LeaveManagementDAO() {
        // Initialize MongoDB connection
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("employeeLeaveDB");
        this.leaveCollection = database.getCollection("leaveRequests");
//        this.employeeDao = new EmployeeDaoImpl();
    }

    public void applyLeave(LeaveRequest leaveRequest) {
        Document document = new Document()
                .append("employeeId", leaveRequest.getEmployeeId())
                .append("leaveType", leaveRequest.getLeaveType())
                .append("startDate", leaveRequest.getStartDate())
                .append("endDate", leaveRequest.getEndDate())
                .append("numberOfDays", leaveRequest.getNumberOfDays())
                .append("reason", leaveRequest.getReason())
                .append("status", leaveRequest.getStatus())
                .append("applicationDate", leaveRequest.getApplicationDate());

        leaveCollection.insertOne(document);
        System.out.println("Leave request submitted successfully!");
    }

    public void updateLeaveStatus(String leaveId, String status, String comments) {
        Document query = new Document("_id", new ObjectId(leaveId));
        Document update = new Document("$set", new Document()
                .append("status", status)
                .append("approverComments", comments));

        leaveCollection.updateOne(query, update);
        System.out.println("Leave request status updated successfully!");
    }

    public List<LeaveRequest> getEmployeeLeaveHistory(int employeeId) {
        List<LeaveRequest> leaveHistory = new ArrayList<>();
        FindIterable<Document> documents = leaveCollection.find(
                new Document("employeeId", employeeId)
        );

        for (Document doc : documents) {
            LeaveRequest leave = new LeaveRequest(
                    doc.getInteger("employeeId"),
                    doc.getString("leaveType"),
                    doc.getDate("startDate"),
                    doc.getDate("endDate"),
                    doc.getString("reason")
            );
            leave.setId(doc.getObjectId("_id").toString());
            leave.setStatus(doc.getString("status"));
            if (doc.getString("approverComments") != null) {
                leave.setApproverComments(doc.getString("approverComments"));
            }
            leaveHistory.add(leave);
        }

        return leaveHistory;
    }

    public List<LeaveRequest> getPendingLeaves() {
        List<LeaveRequest> pendingLeaves = new ArrayList<>();
        FindIterable<Document> documents = leaveCollection.find(
                new Document("status", "PENDING")
        );

        for (Document doc : documents) {
            LeaveRequest leave = new LeaveRequest(
                    doc.getInteger("employeeId"),
                    doc.getString("leaveType"),
                    doc.getDate("startDate"),
                    doc.getDate("endDate"),
                    doc.getString("reason")
            );
            leave.setId(doc.getObjectId("_id").toString());
            pendingLeaves.add(leave);
        }

        return pendingLeaves;
    }
}