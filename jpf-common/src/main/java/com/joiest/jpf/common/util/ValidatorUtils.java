package com.joiest.jpf.common.util;


import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 验证工具类
 * Created by zjf1650 on 14/07/2017.
 */
public abstract class ValidatorUtils {
	
	private static final Logger logger = LogManager.getLogger(ValidatorUtils.class);

	private static Validator validator;

	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public static <T> void validate(T target) {
//		if(target == null){
//			throw new PtsException(PtsErrorInfo.INVALID_PARAMETER,
//					"参数不能为空");
//		}
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(target);
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			ConstraintViolation<T> convi = constraintViolations.iterator()
					.next();
			logger.info("#Param Validator throw exception: < "+convi.getMessage()+" >");
			throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,
					convi.getMessage());
		}
	}

	public static <T> void validateInterface(T target) {
//		if(target == null){
//			throw new PtsException(PtsErrorInfo.INVALID_PARAMETER,
//					"参数不能为空");
//		}
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(target);
		if (constraintViolations != null && !constraintViolations.isEmpty()) {

			ConstraintViolation<T> convi = constraintViolations.iterator().next();
			logger.info("#Param Validator throw exception: < "+convi.getMessage()+" >");
			throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), convi.getMessage());
		}
	}

}
