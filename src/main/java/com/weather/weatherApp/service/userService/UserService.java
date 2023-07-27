package com.weather.weatherApp.service.userService;

import java.util.List;

import org.springframework.stereotype.Service;


import com.weather.weatherApp.model.MembersEntity;
import com.weather.weatherApp.model.RolesEntity;
import com.weather.weatherApp.repository.MembersRepository;
import com.weather.weatherApp.repository.RolesRepository;


@Service
public class UserService implements IUserService {
	
	private MembersRepository membersRepository;
	private RolesRepository rolesRepository;
	
	

	public UserService(MembersRepository membersRepository,RolesRepository rolesRepository) {
		super();
		this.membersRepository = membersRepository;
		this.rolesRepository = rolesRepository;
	}



	@Override
	public void addMembers(MembersEntity membersEntity) {
		
		membersRepository.save(membersEntity);
		
		
	}
	
	@Override
	public void addRoles(RolesEntity rolesEntity) {
		
		rolesRepository.save(rolesEntity);
		
	}
	
	
	@Override
	public List<MembersEntity> getMembersForPage(int page, int pageSize) {
		List<MembersEntity> allMembers = membersRepository.findAll();

        // Sayfa numarasını kontrol edin ve geçerli aralıkta olup olmadığını kontrol edin
        int totalUsers = allMembers.size();
        int totalPages = (totalUsers + pageSize - 1) / pageSize;

        if (page < 1) {
            page = 1;
        } else if (page > totalPages) {
            page = totalPages;
        }

        // İlgili sayfadaki kullanıcıları seçin
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalUsers);

        return allMembers.subList(startIndex, endIndex);
	}
	
	
	

}
