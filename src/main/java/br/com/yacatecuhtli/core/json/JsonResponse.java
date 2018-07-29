package br.com.yacatecuhtli.core.json;

import br.com.yacatecuhtli.core.message.JsonErrorMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class JsonResponse {

    protected Object attributes;

    protected JsonResponseMetadata meta;

    protected List<JsonErrorMessage> errors = new ArrayList<>();

    public void setErrors(List<JsonErrorMessage> errors) {
        Optional.ofNullable(errors).orElseGet(ArrayList::new).forEach(this::addError);
    }

    public void addError(JsonErrorMessage error) {
        this.errors.add(error);
    }

}
