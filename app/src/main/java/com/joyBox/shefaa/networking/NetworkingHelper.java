package com.joyBox.shefaa.networking;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Adhamkh on 2018-08-10.
 */

public class NetworkingHelper {

    public static int RequestTimeout = 20000;

    public static String ErrorConnectionResponse = "ErrorConnection";
    public static String EmailNotFoundResponse = "EmailNotFound";

    public static String Mainurl = "http://shefaaonline.net/api/";
    public static String TokenURL = "http://shefaaonline.net/services/session/token";

    public static String SignInURL = "user/login";
    public static String SignUpURL = "user/register";
    public static String ForgotPasswordURL = "user/request_new_password";
    public static String ChangeEmailUrl = "http://shefaaonline.net/api/user/";//[UID].json
    public static String ChangePasswordUrl = "http://shefaaonline.net/api/user/";

    public static String LogoutUrl = "http://shefaaonline.net/api/user/logout";

    public static String GeneralProfile_Update_Url = "http://shefaaonline.net/update-profile";


    public static String ReplayMessageUrl = "http://shefaaonline.net/send-new-pm";

    public static String TermsAndConditionsUrl = "http://shefaaonline.net/api/legal/get_conditions.json";

    public static String NotificationRegisterDeviceUrl = "http://shefaaonline.net/api/push_notifications";

    public static String MyNotificationsUrl = "http://shefaaonline.net/api/notifications_service";

    public static String FlushNotificationsUrl = "http://shefaaonline.net/api/push_notifications/";

    public static String MessageUnReaded = "http://shefaaonline.net/count-pm";

    public static String MessageALL = "http://shefaaonline.net/load-pm";

    public static String MakeReadedURL = "http://shefaaonline.net/markasread-pm/";

    public static String AppointmentPatientListUrl = "http://shefaaonline.net/api/patient_appointments?patient_uid=";

    public static String PrescriptionUrl = "http://shefaaonline.net/api/show_prescription_service";

    public static String TestResultUrl = "http://shefaaonline.net/api/my-tests-service?patient_id=";

    public static String MedicineAndPotionDetailsUrl = "http://shefaaonline.net/api/medicine_and_potion?item_id=";

    public static String PrescriptionFollowUpUrl = "http://shefaaonline.net/api/indicators-to-follow-up?item_id=";

    public static String MagazinePostsUrl = "http://shefaaonline.net/api/medical-magazine-service";

    public static String MagazinePostCommentsUrl = "http://shefaaonline.net/api/node/";

    public static String Doctors_Short_List_ServiceUrl = "http://shefaaonline.net/api/doctors_short_list_service";

    public static String MedicalProfile_Url = "http://shefaaonline.net/view-profile";


    public static String Doctor_Search_Url = "http://shefaaonline.net/api/doctors_list_service";

    public static String Appointment_Available_Time_Url = "http://shefaaonline.net/get-available-times";

    public static String Appointment_Book_Url = "http://shefaaonline.net/validate-book-appointment";


    public static String MedicinepotionUrl = "http://shefaaonline.net/api/show_prescription_service_medicine";


    public static String MagazinePostPrefixUrl = "http://shefaaonline.net/sites/default/files/styles/";

    public static String MagazinePostSuffixUrl = "/public/";

    public static String MagazinePostShareUrl = "http://shefaaonline.net/node/";

    public static String MagazinePostLikeUrl = "http://shefaaonline.net/api/flag/flag";
    public static String MagazinePostCommentAddUrl = "http://shefaaonline.net/api/comment.json";


    public static String SelfMonitorListUrl = "http://shefaaonline.net/api/my-self-monitor-service";

    public static String MyGuardiansListUrl = "http://shefaaonline.net/api/my-guardians-service";

    public static String Specialist_AutoCompleteUrl = "http://shefaaonline.net/api/taxonomy_term?parameters[vid]=11";


    public static final String Pharmacy_Search_Url = "http://shefaaonline.net/api/search_medicine_service";

    public static final String Lab_Search_Url = "http://shefaaonline.net/api/search_lab_service";

    public static final String All_User_AutoComplete_Url = "http://shefaaonline.net/api/all-users";


    public static String about_usurl = "http://shefaaonline.net/about-us";
    public static String contact_usurlar = "http://shefaaonline.net/ar/contact-us";
    public static String contact_usurl = "http://shefaaonline.net/contact-us";

    public static String registerurl = "http://shefaaonline.net/user/register";

    public static String ApproveAppointmenturl = "http://shefaaonline.net/api-approve-appointment";


    public static String Urgent = "0";
    public static String UnUrgent = "1";


//    public static Client client = new Client();
}
