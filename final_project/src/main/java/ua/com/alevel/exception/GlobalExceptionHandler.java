package ua.com.alevel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ModelAndView entityNotFoundExceptionHandler(EntityNotFoundException exception) {
        return generateModelAndView(exception.getMessage());
    }

    @ExceptionHandler(value = WrongPasswordException.class)
    public ModelAndView wrongPasswordExceptionHandler(WrongPasswordException exception) {
        return generateModelAndView(exception.getMessage());
    }

    @ExceptionHandler(value = IncorrectDataInput.class)
    public ModelAndView incorrectInputDataExceptionHandler(IncorrectDataInput exception) {
        return generateModelAndView(exception.getMessage());
    }

    @ExceptionHandler(value = NonActiveTripException.class)
    public ModelAndView nonActiveTripExceptionHandler(NonActiveTripException exception) {
        return generateModelAndView(exception.getMessage());
    }

    @ExceptionHandler(value = AlreadyExistEntity.class)
    public ModelAndView alreadyExistEntityExceptionHandler(AlreadyExistEntity exception) {
        return generateModelAndView(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView globalExceptionHandling(Exception exception) {
        exception.printStackTrace();
        return generateModelAndView("unknown error");
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ModelAndView constraintViolationErrorHandler(ConstraintViolationException exception) {
        return generateModelAndView(exception.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ModelAndView methodArgumentNotValidErrorHandler(MethodArgumentNotValidException exception) {
        return generateModelAndView(exception.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ModelAndView methodArgumentNotValidErrorHandler(MethodArgumentTypeMismatchException exception) {
        return generateModelAndView("incorrect value");
    }

    private ModelAndView generateModelAndView(String msg) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", msg);
        mav.setViewName("error");
        return mav;
    }
}
