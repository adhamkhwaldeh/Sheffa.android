package com.joyBox.shefaa.networking;

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

    public static String ReplayMessageUrl = "http://shefaaonline.net/send-new-pm";

    public static String TermsAndConditionsUrl = "http://shefaaonline.net/api/legal/get_conditions.json";

    public static String NotificationRegisterDeviceUrl = "http://shefaaonline.net/api/push_notifications";

    public static String MyNotificationsUrl = "http://shefaaonline.net/api/notifications_service";


    public static String pharmacySearchURL = "search_medicine_service";
    public static String DoctorSearchURL = "doctors_list_service";
    public static String PrescriptionUrl = "http://shefaaonline.net/api-prescription";
    public static String AppointmentURL = "http://shefaaonline.net/get-available-times";
    public static String RequestAppointmentUrl = "http://shefaaonline.net/validate-book-appointment";
    public static String RatingURL = "http://shefaaonline.net/rate";
    public static String AppointmentListUrl = "http://shefaaonline.net/api-appointment";
    public static String appointment_updateURL = "http://shefaaonline.net/api-update-appointment";
    public static String PharmacyMedicineURL = "http://shefaaonline.net/api-medicine-amount";
    public static String PharmacyMedicineUpdateURL = "http://shefaaonline.net/update-amount";
    public static String DoctorId = "DoctorId";
    public static String TargetId = "TargetId";
    public static String PharmacyId = "PharmacyId";

    public static String MessageUnReaded = "http://shefaaonline.net/count-pm";

    public static String MessageALL = "http://shefaaonline.net/load-pm";

    public static String MedicinepotionUrl = "http://shefaaonline.net/api/show_prescription_service_medicine";

    public static String MakeReadedURL = "http://shefaaonline.net/markasread-pm/";

    public static String about_usurl = "http://shefaaonline.net/about-us";
    public static String contact_usurlar = "http://shefaaonline.net/ar/contact-us";
    public static String contact_usurl = "http://shefaaonline.net/contact-us";

    public static String registerurl = "http://shefaaonline.net/user/register";

    public static String ApproveAppointmenturl = "http://shefaaonline.net/api-approve-appointment";

    public static String Notificationurl = "http://shefaaonline.net/api/push_notifications";

    public static String Urgent = "0";
    public static String UnUrgent = "1";

//    public static Client client = new Client();
}
