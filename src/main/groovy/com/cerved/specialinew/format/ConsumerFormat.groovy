package com.cerved.specialinew.format

enum ConsumerFormat {

    EMPTY_FILE("Nessun file", 0),
    CAMPI_VARIABILI("Campi variabili", 1)


    private static Map<String, ConsumerFormat> map = new HashMap<String, ConsumerFormat>();
    private String value;
    private Integer id;

    static {
        for (ConsumerFormat o : ConsumerFormat.values()) {
            map.put(o.value, o);
        }
    }

    private ConsumerFormat(String value, Integer id) {
        this.value = value;
        this.id = id;
    }

    public static ConsumerFormat byValue(String value) {
        return map.value
    }

    public static ConsumerFormat byId(Integer value) {
        for (ConsumerFormat o : ConsumerFormat.values()) {
            if (o.id.equals(value)) {
                return o;
            }
        }
        return null;
    }

    public static List<ConsumerFormat> displayList() {
        return new ArrayList<ConsumerFormat>(map.values().sort());
    }

    public String getValue() {
        return value;
    }

    public Integer getId() {
        return id;
    }
}
