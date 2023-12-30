package com.ims.utility;

import java.util.ArrayList;
import java.util.List;

import com.ims.dao.impl.InsuranceCategoryDAOImpl;
import com.ims.dao.impl.UserDAOImpl;
import com.ims.pojo.InsuranceCategory;
import com.ims.pojo.InsurancePolicy;
import com.ims.pojo.InsuranceSubCategory;
import com.ims.pojo.User;

/**
 * This class loads one admin and one client user Also adds 3 categories, 2
 * sub-categories each and one policy under each
 */
public class DataLoader {
	static UserDAOImpl udi = new UserDAOImpl();
	static InsuranceCategoryDAOImpl icdi = new InsuranceCategoryDAOImpl();

	public static void load() {
		List<User> users = udi.viewUsers();
		List<InsuranceCategory> insuranceCategory = icdi.viewCategory();
		users.add(new User(null, null, null, "admin", "admin", "admin"));
		users.add(new User("tm", "TM", "tm", "tm", "tm", "customer"));

		InsuranceCategory c1 = new InsuranceCategory("Life Insurance");

		InsurancePolicy p11 = new InsurancePolicy("Whole Life Insurance Policy", 100000);
		InsurancePolicy p12 = new InsurancePolicy("Term Life Insurance Policy", 50000);

		List<InsurancePolicy> pl1 = new ArrayList<>();
		List<InsurancePolicy> pl2 = new ArrayList<>();
		pl1.add(p11);
		pl2.add(p12);

		InsuranceSubCategory s1 = new InsuranceSubCategory("Whole Life Insurance");
		InsuranceSubCategory s2 = new InsuranceSubCategory("Term Life Insurance");
		s1.setPolicies(pl1);
		s2.setPolicies(pl2);

		List<InsuranceSubCategory> sub1 = new ArrayList<InsuranceSubCategory>();
		sub1.add(s1);
		sub1.add(s2);

		c1.setSubcat(sub1);

		insuranceCategory.add(c1);
		// ---------------------------------------
		InsuranceCategory c2 = new InsuranceCategory("Health Insurance");

		InsurancePolicy p21 = new InsurancePolicy("Individual Health Insurance Policy", 100000);
		InsurancePolicy p22 = new InsurancePolicy("Group Health Insurance Policy", 50000);

		List<InsurancePolicy> pl21 = new ArrayList<>();
		List<InsurancePolicy> pl22 = new ArrayList<>();
		pl21.add(p21);
		pl22.add(p22);

		InsuranceSubCategory s21 = new InsuranceSubCategory("Individual Health Insurance");
		InsuranceSubCategory s22 = new InsuranceSubCategory("Group Health Insurance");
		s21.setPolicies(pl21);
		s22.setPolicies(pl22);

		List<InsuranceSubCategory> sub2 = new ArrayList<InsuranceSubCategory>();
		sub2.add(s21);
		sub2.add(s22);

		c2.setSubcat(sub2);

		insuranceCategory.add(c2);
		// ---------------------------------------
		InsuranceCategory c3 = new InsuranceCategory("Auto Insurance");

		InsurancePolicy p31 = new InsurancePolicy("Collision Insurance Policy", 100000);
		InsurancePolicy p32 = new InsurancePolicy("Liability Insurance Policy", 50000);

		List<InsurancePolicy> pl31 = new ArrayList<>();
		List<InsurancePolicy> pl32 = new ArrayList<>();
		pl31.add(p31);
		pl32.add(p32);

		InsuranceSubCategory s31 = new InsuranceSubCategory("Collision Insurance");
		InsuranceSubCategory s32 = new InsuranceSubCategory("Liability Insurance");
		s31.setPolicies(pl31);
		s32.setPolicies(pl32);

		List<InsuranceSubCategory> sub3 = new ArrayList<InsuranceSubCategory>();
		sub3.add(s31);
		sub3.add(s32);

		c3.setSubcat(sub3);

		insuranceCategory.add(c3);
	}

}
