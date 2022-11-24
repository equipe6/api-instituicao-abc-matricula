package br.edu.unisinos.instituicaoabc.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * BaseDto é uma classe abstrata para uso em Entities.
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 6278178885309254907L;

    /**
     * Sobreescrita do ToString para objetos em fortmato Json sem identação(Tudo em uma Linha).
     */
    @Override
    public String toString() {
        return this.toString(false);
    }

    /**
     * ToString para simplificar os logs dos objetos em formato Json.
     * Parametro prettyPrinter se verdadeiro é responsavel por gerar json indentado em multiplas linhas.
     */
    public String toString(boolean prettyPrinter) {
        try {
            if (prettyPrinter) {
                return new ObjectMapper() //
                        .setSerializationInclusion(JsonInclude.Include.NON_NULL) //
                        .writerWithDefaultPrettyPrinter() //
                        .writeValueAsString(this);
            } else {
                return new ObjectMapper() //
                        .setSerializationInclusion(JsonInclude.Include.NON_NULL) //
                        .writeValueAsString(this);
            }
        } catch (JsonProcessingException e) {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    public String toStringOriginal() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

}
