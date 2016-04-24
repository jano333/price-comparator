package sk.hudak.pricecomparator.middle.to;

/**
 * Created by hudak on 18.04.2016.
 */
public abstract class BasicStepDto {

    private ErrorStatus errorStatus;

    private boolean isError() {
        return errorStatus != null;
    }

    public ErrorStatus getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(ErrorStatus errorStatus) {
        this.errorStatus = errorStatus;
    }
}
