// ResponseEmpresa.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.app_integradora.Retroft;
import java.time.OffsetDateTime;
import java.util.List;

public class ResponseEmpresa {
    String message;
    Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    class Data{
        String nombre;
        String uptaded_at;
        String created_at;
        String id;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getUptaded_at() {
            return uptaded_at;
        }

        public void setUptaded_at(String uptaded_at) {
            this.uptaded_at = uptaded_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}