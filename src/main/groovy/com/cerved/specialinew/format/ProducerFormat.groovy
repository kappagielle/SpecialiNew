package com.cerved.specialinew.format

import com.cerved.specialinew.producer.*
import com.cerved.specialinew.execution.DataProducer

enum ProducerFormat {

    STATIC_PRODUCER("Produttore di test", StaticProducer.class, 1),
    EXECUTE_QUERY("Esegui query personalizzata", ExecuteQueryProducer.class, 2)


    private static Map<String, ProducerFormat> _map = new HashMap<String, ProducerFormat>()
    private String _value
    private Integer _id
    private Class _class

    static {
        for (ProducerFormat o : ProducerFormat.values()) {
            _map.put(o._value, o)
        }
    }

    private ProducerFormat(String value, Class c, Integer id) {
        _value = value
        _class = c
        _id = id
    }

    public static ProducerFormat byValue(String value) {
        return _map.value
    }

    public static ProducerFormat byId(Integer value) {
        for (ProducerFormat o : ProducerFormat.values()) {
            if (o._id.equals(value)) {
                return o
            }
        }
        return null;
    }

    public static List<ProducerFormat> displayList() {
        return new ArrayList<ProducerFormat>(_map.values().sort())
    }

    public static DataProducer createProducer(ProducerFormat format) {
        for (ProducerFormat f in ProducerFormat.values()) {
            if (format == f) {
                return f._class.newInstance()
            }
        }
        return null
    }

    public String getValue() {
        return _value
    }

    public static String getDescription(Integer value) {

        for (ProducerFormat o : ProducerFormat.values()) {
            if (o._id.equals(value)) {
                return o.value.toString()
            }
        }
        return ""
    }

    public Integer getId() {
        return _id
    }


}
