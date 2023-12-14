package com.example.app_integradora.Retroft;

import java.util.List;

public class GetEmpresa {
    private List<Empresa> empresas;
    private long userId;

    public List<Empresa> getEmpresas() { return empresas; }
    public void setEmpresas(List<Empresa> value) { this.empresas = value; }

    public long getUserId() { return userId; }
    public void setUserId(long value) { this.userId = value; }

    public static class Empresa {

        private long id;
        private long userid;
        private long empresaid;
        private String createdAt;
        private String updatedAt;
        private String nombre;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getUserid() {
            return userid;
        }

        public void setUserid(long userid) {
            this.userid = userid;
        }

        public long getEmpresaid() {
            return empresaid;
        }

        public void setEmpresaid(long empresaid) {
            this.empresaid = empresaid;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }


    }
}
