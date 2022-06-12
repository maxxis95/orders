package com.rohlik.orders.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A response indicating that the request was not valid (e.g. a mandatory field is missing)
 */
public class ValidationErrorResponse
{
    @JsonProperty("error")
    private ErrorResponseObjectWithDetails error;

    public ValidationErrorResponse error(ErrorResponseObjectWithDetails error)
    {
        this.error = error;
        return this;
    }

    /**
     * Get error
     *
     * @return error
     **/

    public ErrorResponseObjectWithDetails getError()
    {
        return error;
    }

    public void setError(ErrorResponseObjectWithDetails error)
    {
        this.error = error;
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
        ValidationErrorResponse validationErrorResponse = (ValidationErrorResponse) o;
        return Objects.equals(this.error, validationErrorResponse.error);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(error);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class ValidationErrorResponse {\n");

        sb.append("    error: ").append(toIndentedString(error)).append("\n");
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

