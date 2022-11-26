package co.grandcircus.EmployeeApi.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import co.grandcircus.EmployeeApi.Model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

	//finds employee by ObjectId, then removes the shift with the specified shiftId
	@Query("{'id':?0}")
	@Update("{$pull: {'schedule': {'id':?1}}}")
	void updateById(String id, String shiftId);
	
	@Query("{'id':?0}")
	@Update("{$push: {'schedule': {'_id': ObjectId, 'shiftName':?1, 'date':?2, 'startTime':?3, 'endTime':?4}}}")
	void updateById(String id, String shiftName, String date, String startTime, String endTime);
	
	
//db.employees.updateOne({
//	"_id": ObjectId("actualIdGoesHere")
//},
//		{
//	"$push": {
//		"schedule": { "_id": ObjectId(),
//			"shiftName": "actualShiftNameHere",
//			"date": "actualDateHere",
//			"startTime": "actualStartTimeHere",
//			"endTime": "actualEndTimeHere"
//		}
//	}
//		})
	
	
	//********First method********
	//db.employees.updateOne({
//	  "_id": ObjectId("id")
//	  },
//	  {
//	    "$pull": {
//	      "schedule": {
//	        "_id": ObjectId("shiftId")
//	      }
//	    }
//	  })

}
