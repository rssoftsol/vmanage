package com.imanage.dao.security;

import com.imanage.models.ClubDetails;

public interface ClubDetailsDao {
	public ClubDetails checkLoginDetails(String loginName);
}
