package com.rohlik.orders.dto;

import java.util.Objects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Basic error response according to the guidlines from https://github.com/microsoft/api-guidelines/blob/vNext/Guidelines.md#7102-error-condition-responses
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-04-13T15:00:45.987494700+02:00[Europe/Budapest]")
public class ErrorResponseObject
{
    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    public ErrorResponseObject code(String code)
    {
        this.code = code;
        return this;
    }

    /**
     * The code specific to the type of error that occurred.
     *
     * @return code
     **/

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public ErrorResponseObject message(String message)
    {
        this.message = message;
        return this;
    }

    /**
     * The error message
     *
     * @return message
     **/

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        ErrorResponseObject errorResponseObject = (ErrorResponseObject) o;
        return Objects.equals(this.code, errorResponseObject.code) &&
            Objects.equals(this.message, errorResponseObject.message);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(code, message);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorResponseObject {\n");

        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o)
    {
        if (o == null)
        {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

