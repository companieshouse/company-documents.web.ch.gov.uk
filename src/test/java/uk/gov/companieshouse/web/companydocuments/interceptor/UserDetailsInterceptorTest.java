package uk.gov.companieshouse.web.companydocuments.interceptor;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;
import uk.gov.companieshouse.web.companydocuments.session.SessionService;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDetailsInterceptorTest {

    private static final String USER_EMAIL = "userEmail";

    private static final String SIGN_IN_KEY = "signin_info";

    private static final String USER_PROFILE_KEY = "user_profile";

    private static final String EMAIL_KEY = "email";

    private static final String EMAIL_ADDRESS = "emailAddress";

    @Mock
    private SessionService sessionService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private Object handler;

    @Mock
    private ModelAndView modelAndView;

    @Mock
    private Map<String, Object> sessionData;

    @Mock
    private Map<String, Object> signinInfo;

    @Mock
    private Map<String, Object> userProfile;

    private UserDetailsInterceptor userDetailsInterceptor;

    @BeforeEach
    private void setup() {

        this.userDetailsInterceptor = new UserDetailsInterceptor(sessionService);
    }

    @Test
    @DisplayName("Post handle - null model and view")
    void postHandleNullModelAndView() {

        assertAll(() -> userDetailsInterceptor.postHandle(request, response, handler, null));

        verify(sessionService, never()).getSessionDataFromContext();
    }

    @Test
    @DisplayName("Post handle - post request")
    void postHandlePostRequest() {

        when(request.getMethod()).thenReturn("POST");

        assertAll(() -> userDetailsInterceptor.postHandle(request, response, handler, modelAndView));

        verify(sessionService, never()).getSessionDataFromContext();
    }

    @Test
    @DisplayName("Post handle - get request")
    void postHandleGetRequest() {

        when(request.getMethod()).thenReturn("GET");

        when(sessionService.getSessionDataFromContext()).thenReturn(sessionData);

        when(sessionData.get(SIGN_IN_KEY)).thenReturn(signinInfo);

        when(signinInfo.get(USER_PROFILE_KEY)).thenReturn(userProfile);

        when(userProfile.get(EMAIL_KEY)).thenReturn(EMAIL_ADDRESS);

        assertAll(() -> userDetailsInterceptor.postHandle(request, response, handler, modelAndView));

        verify(modelAndView).addObject(USER_EMAIL, EMAIL_ADDRESS);
    }
}
