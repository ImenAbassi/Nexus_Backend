package com.example.nexus.Exceptions.handler;

public enum ErrorCodes {
    /*
    Role
     */
    Role_Not_Valid(1190),
    Role_Not_Found(1191),
    Role_Used_By_Other_Table(1192),
    Pas_De_Role_Dans_La_Base(1193),
    ENTITY_NOT_FOUND_EXCEPTION(1194),
    /*
    PorteuseMenuRole
     */
    PorteuseMenuRole_Not_Valid(1290),
    PorteuseMenuRole_Not_Found(1291),
    PorteuseMenuRole_Used_By_Other_Table(1292),
    Pas_De_PorteuseMenuRole_Dans_La_Base(1293),
    /*
    PorteuseMenuRole
     */
    Menu_Not_Valid(1300),
    Menu_Not_Found(1301),
    Menu_Used_By_Other_Table(1302),
    Pas_De_Menu_Dans_La_Base(1303),
    PROBLEME_INITIALISATION_BASE_MENU(1304),
    /*
    Hotel
     */
    HOTEL_Not_Valid(1310),
    HOTEL_Not_Found(1311),
    HOTEL_Used_By_Other_Table(1312),
    Pas_De_HOTEL_Dans_La_Base(1313),
    PROBLEME_INITIALISATION_BASE_HOTEL(1314),
    /*
    EXCEPTIONS ERRRSCODES
     */
    ERROR_SCRIPT(2000),
    ERROR_AUTHORITIES(2001),
    ERROR_SECURITY(2002),
    ERROR_ELEMENET_NOT_FOUND(2003),
    MESSAGING_EXCEPTION(2004),
    PARSE_EXCEPTION(2005),
    IO_EXCEPTION(2006),
    NUMBER_FORMAT_EXCEPTION(2007),
    EXCEPTION(2008),
    FILE_NOT_FOUND_EXCEPTION(2009),
    USER_NAME_NOT_FOUND_EXCEPTION(2010),
    NAMING_EXCEPTION(2011),
    ILLEGAL_ARGUMENT_EXCEPTION(2012),
    EXPIRED_JWT_EXCEPTION(2013),
    CONSTRAINT_VIOLATION_EXCEPTION(2014),
    JSON_PROCESSING_EXCEPTION(2015),
    DUPLICATION_EXCEPTION(2016);

    private final int code;

    private ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ErrorCodes getEnumByCode(int id) {
        for (ErrorCodes e : values()) {
            if (e.getCode() == id) {
                return e;
            }
        }
        return null;
    }

}
