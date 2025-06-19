package com.booking.movie.Common;
public class Constants {

    public static final String ROW_ID = "rowId";
    public static final String ROW_IDS = "rowIds";
    public static final String ERROR_ITEM = "item";
    public static final String ERROR_CODE = "errorCode";
    public static final String ERROR_PARAMS = "errorParams";
    public static final int RESPONSE_SUCCESS = 0;
    public static final int RESPONSE_FAILED = 1;
    public static final String EMAIL = "email";
    public static final String USER_NAME_LOGIN = "userNameLogin";
    public static final String PASSWORD = "password";
    public static final String LOGIN_FAIL = "Login failed";
    public static final String VALIDATE = "validate";
    public static final String ET_HEADER_ID = "etHeaderId";
    public static final String ACTUAL_HEADER_ID = "actualHeaderId";
    public static final String ROW_INDEX = "rowIndex";
    public static final String DASH = "_";
    public static final String ET_TYPE = "etType";
    public static final String SCREEN = "screen";
    public static final String SCREEN_MODE = "screenMode";
    public static final String EXPORT_PRODUCTIVITY_SHEET = "Năng suất";
    public static final String EXPORT_OTHER_INFO_SHEET = "Thông tin khác";
    public static final String EXPORT_OTHER_EFFORT_SHEET = "Công số khác";
    public static final String EXPORT_NON_FUNC_SHEET = "Non-functional size";
    public static final String EXPORT_SYNTHETIC_SHEET = "Tổng hợp estimate";
    public static final String EXPORT_FUNC_SHEET = "Functional size";
    public static final String OTHER_EFFORT_TYPE = "OTHER_EFFORT_TYPE";
    public static final String NON_FUNCTIONAL_TYPE = "NON_FUNCTIONAL_TYPE";
    public static final String ITEM_SIZE = "ITEM_SIZE";
    public static final String ITEM_SIZE_MIGRATION = "ITEM_SIZE_MIGRATION";
    public static final String EXCEL_PREFIX_ET_HEADER = "**etHeader";
    public static final String EXCEL_PREFIX_ACT_HEADER = "**actualHeader";
    public static final String EXCEL_PREFIX_OTHER_INFO = "**otherInfo";
    public static final String EXCEL_PREFIX_LIST_ET_OTHER_EFFORT = "*list*etOtherEfforts";
    public static final String EXCEL_PREFIX_LIST_AT_OTHER_EFFORT = "*list*atOtherEfforts";
    public static final String EXCEL_PREFIX_LIST_NON_FUNC = "*list*nonFunctional";
    public static final String EXCEL_PREFIX_GENERAL_INFO = "**generalInfo";
    public static final String EXCEL_PREFIX_ESTIMATE = "**estimate";
    public static final String EXCEL_PREFIX_ACTUAL = "**actual";
    public static final String EXCEL_PREFIX_LIST_TECH_MIGRATE = "*list*techMigrate";
    public static final String EXCEL_PREFIX_LIST_ET_DETAIL_REQ = "*list*etDetailRequirementList";
    public static final String EXCEL_PREFIX_LIST_ACT_DETAIL_REQ = "*list*actualDetailRequirementList";

    public enum StaticStep {
        STEP_1('1'),
        STEP_2('2'),
        STEP_3('3'),
        STEP_4('4'),
        STEP_5('5'),
        STEP_6('6'),
        STEP_7('7');
        private Character value;

        private StaticStep(Character value) {
        this.value = value;
        }

        public Character getValue(){return value;}
    }
    
    public enum Static {
        STATIC_NO_APPROVED_YET('0', "Chưa phế duyệt"), STATIC_APPROVED('1', "Đã phê duyệt"), STATIC_EXCEPTIONS('2',
                "Exeption");

        private Character value;
        private String statusName;

        private Static(Character value, String statusName) {
            this.value = value;
            this.statusName = statusName;
        }

        public Character getValue() {
            return value;
        }

        public String getStatusName() {
            return statusName;
        }
    }

    public enum ScreenMode {
        LIST_HEADER("LIST_HEADER"), LIST_DETAIL("LIST_DETAIL");

        private String value;

        private ScreenMode(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum EtType {
        ET_NEW("ET_NEW"),
        ET_MIGRATION("ET_MIGRATION");

        private String value;

        private EtType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Major {
        MAJOR_MANAGE_01("major_manage_01"),
        MAJOR_OTHER_01("major_other_01"),
        MAJOR_SALE_01("major_sale_01");

        private String value;

        private Major(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum ProjectType {
        PROJECT_TYPE_MOBILE_01("project_type_mobile_01"),
        PROJECT_TYPE_OTHER_01("project_type_other_01"),
        PROJECT_TYPE_WEB_01("project_type_web_01");

        private String value;

        private ProjectType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    
    
    public enum FielType {
        TEXT("Text"),
        PULLDOWN("Pulldown"),
        NUMBER("Number");
        
        private String value;

        private FielType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
