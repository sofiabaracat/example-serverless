package com.example;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;


public class Function {
    
    DAO dao = new DAO();
    
    @FunctionName("criarcidade")
    public Cidade create(
            @HttpTrigger(name = "criacidade", 
                    methods = {HttpMethod.POST}, 
                    route = "cidade") Cidade c)
    {
        return dao.create(c);
    }
    
    @FunctionName("lercidades")
    public List<Cidade> read(
            @HttpTrigger(name = "lecidade", 
                    methods = {HttpMethod.GET}, 
                    route = "cidade") String cidade)
    {
        return dao.read();
    }   
    
    @FunctionName("alterarcidade")
    public Cidade update(
            @HttpTrigger(name = "alteracidade", 
                    methods = {HttpMethod.PUT}, 
                    route = "cidade") Cidade c)
    {
        return dao.update(c);
    }
    
    @FunctionName("deletarcidade")
    public int delete(
            @HttpTrigger(name = "deletacidade", 
                    methods = {HttpMethod.DELETE}, 
                    route = "cidade/{id}") @BindingName("id") Long id)
    {
        return dao.delete(id);
    }
}
    


class Estado {
        private Long id;
        private String nome;

        public Estado(Long id, String nome){
            this.nome = nome;
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

}


 class Cidade {
        private Long id;
        private String nome;
        private Estado estado;

        public Cidade(Long id, String nome, Estado estado){
            this.id = id;
            this.nome = nome;
            this.estado = estado;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
        
        public Estado getEstado() {
            return estado;
        }

        public void setEstado(Estado estado) {
            this.estado = estado;
        }
}

class DAO{
    public Cidade create(Cidade c){
        return c;
    }
    
    public List<Cidade> read(){
        return Stream.of(
                new Cidade(1L,"Garça", new Estado(1L,"São Paulo")),
                new Cidade(2L,"Florianopolis", new Estado(2L,"Santa Catarina")),
                new Cidade(3L,"Cornélio Procópio", new Estado(3L,"Paraná"))
        ).collect(Collectors.toList());
    }
    
    public Cidade update (Cidade c){
        c.setNome(c.getNome()+"-updated");
        return c;
    }
    
    public int delete(Long id){
        return 200;
    }
}