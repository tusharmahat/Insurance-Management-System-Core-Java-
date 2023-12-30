package com.ims.dao;

import java.util.List;

import com.ims.pojo.InsurancePolicy;

public interface InsurancePolicyDAO {
	void addPolicy(String cid, String scid); // ADD

	List<InsurancePolicy> viewPolicy(String cid, String scid); // VIEW ALL

	void updatePolicy(String cid, String scid, String pid); // UPDATE

	boolean deletePolicy(String cid, String scid, String pid); // DELETE

}
