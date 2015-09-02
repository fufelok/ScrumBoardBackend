package se.leanbit.ticketsystem.exception;

public class TicketSystemServiceExcption extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    public TicketSystemServiceExcption(final String message, final Throwable cause)
    {
        super(message, cause);
    }
    public TicketSystemServiceExcption(final String message)
    {
        super(message);
    }
}
