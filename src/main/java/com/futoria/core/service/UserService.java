package com.futoria.core.service;

import com.futoria.core.application.configuration.security.FutoriaSecurityService;
import com.futoria.core.model.user.User;
import com.futoria.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CoreUserService")
@Transactional(value = "txManager", readOnly = true)
public class UserService {
    private UserRepository userRepository;
    private FutoriaSecurityService securityService;

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_PROFILE_USER_READ')")
    public User getUser(Long id) {
        return userRepository.getFirstById(id);
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_USER_MY_HEADMASTER_READ')")
    public User getMyHeadMaster() {
        User headMaster = null;

        try {
            headMaster = securityService.getUserFromContext().getUserData().getGroup().getHeadMaster();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // TODO check headmaster roles

        return headMaster;
    }

    public User getMyData() {
        return userRepository.getFirstById(securityService.getUserFromContext().getId());
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_USER_CREATE')")
    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Autowired
    public void setUserRepository(@Qualifier("CoreUserRepository")
                                          UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSecurityService(@Qualifier("CoreSecurityService") FutoriaSecurityService securityService) {
        this.securityService = securityService;
    }
}