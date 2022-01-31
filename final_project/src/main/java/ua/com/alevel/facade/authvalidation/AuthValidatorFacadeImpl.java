package ua.com.alevel.facade.authvalidation;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ua.com.alevel.service.security.SecurityService;
import ua.com.alevel.web.dto.client.ClientRequestDto;

@Service
public class AuthValidatorFacadeImpl implements AuthValidatorFacade {

    private final SecurityService securityService;

    public AuthValidatorFacadeImpl(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ClientRequestDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClientRequestDto dto = (ClientRequestDto) target;
        if (securityService.existsByEmail(dto.getEmail())) {
            errors.rejectValue("email", "Duplicate.authForm.email");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (dto.getPassword().length() < 8 || dto.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.authForm.password");
        }
        if (!dto.getPasswordConfirm().equals(dto.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.authForm.passwordConfirm");
        }
    }
}
