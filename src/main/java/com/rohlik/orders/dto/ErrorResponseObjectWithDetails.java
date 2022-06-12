package com.rohlik.orders.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Error response optionally including the &#39;traget&#39; and &#39;details&#39; of the error according to the guidlines from https://github.com/microsoft/api-guidelines/blob/vNext/Guidelines.md#7102-error-condition-responses.
 */
public class ErrorResponseObjectWithDetails
{
    @JsonProperty("target")
    private String target;

    @JsonProperty("details")
    private List<ErrorResponseObjectWithDetails> details = null;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    public ErrorResponseObjectWithDetails target(String target)
    {
        this.target = target;
        return this;
    }

    /**
     * The traget where the error occurred, e.g. the name of an invalid property.
     *
     * @return target
     **/

    public String getTarget()
    {
        return target;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public ErrorResponseObjectWithDetails details(List<ErrorResponseObjectWithDetails> details)
    {
        this.details = details;
        return this;
    }

    public ErrorResponseObjectWithDetails addDetailsItem(ErrorResponseObjectWithDetails detailsItem)
    {
        if (this.details == null)
        {
            this.details = new ArrayList<>();
        }
        this.details.add(detailsItem);
        return this;
    }

    /**
     * A list of detail errors that led to the current error.
     *
     * @return details
     **/

    public List<ErrorResponseObjectWithDetails> getDetails()
    {
        return details;
    }

    public void setDetails(List<ErrorResponseObjectWithDetails> details)
    {
        this.details = details;
    }

    public ErrorResponseObjectWithDetails code(String code)
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

    public ErrorResponseObjectWithDetails message(String message)
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
        ErrorResponseObjectWithDetails errorResponseObjectWithDetails = (ErrorResponseObjectWithDetails) o;
        return Objects.equals(this.target, errorResponseObjectWithDetails.target) &&
            Objects.equals(this.details, errorResponseObjectWithDetails.details) &&
            Objects.equals(this.code, errorResponseObjectWithDetails.code) &&
            Objects.equals(this.message, errorResponseObjectWithDetails.message);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(target, details, code, message);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorResponseObjectWithDetails {\n");

        sb.append("    target: ").append(toIndentedString(target)).append("\n");
        sb.append("    details: ").append(toIndentedString(details)).append("\n");
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

