package az.developia.FinalProject.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
	public static String getCurrentUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return auth.getName();

	}
}