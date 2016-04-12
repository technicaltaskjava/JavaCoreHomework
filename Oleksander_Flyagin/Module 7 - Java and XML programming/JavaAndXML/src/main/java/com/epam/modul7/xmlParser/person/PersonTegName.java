package com.epam.modul7.xmlParser.person;

public enum PersonTegName
    {
        SPEECH,
        LINE,
        SPEAKER,
        NEXT;

        public static PersonTegName getElementTagName(String element)
            {
                switch (element)
                    {
                        case "SPEAKER":
                            return SPEAKER;


                        case "LINE":
                            return LINE;

                        case "SPEECH":
                            return SPEECH;

                        default:
                           return NEXT;

                    }

            }
    }
