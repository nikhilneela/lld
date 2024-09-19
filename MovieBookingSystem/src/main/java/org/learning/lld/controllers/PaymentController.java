package org.learning.lld.controllers;

import lombok.NonNull;
import org.learning.lld.models.Booking;
import org.learning.lld.models.User;
import org.learning.lld.services.BookingService;
import org.learning.lld.services.PaymentService;
import org.learning.lld.services.UserService;

public class PaymentController {
    private PaymentService paymentService;
    private UserService userService;
    private BookingService bookingService;

    public PaymentController(@NonNull final PaymentService paymentService, @NonNull final UserService userService, @NonNull final BookingService bookingService) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    public void paymentSuccess(@NonNull final String bookingId, @NonNull final String userId) {
        User user = this.userService.getUser(userId);
        Booking booking = this.bookingService.getBooking(bookingId);
        this.bookingService.confirmBooking(booking, user);
    }

    public void paymentFailed(@NonNull final String bookingId, @NonNull final String userId) {
        User user = this.userService.getUser(userId);
        Booking booking = this.bookingService.getBooking(bookingId);
        this.paymentService.processFailedPayment(booking, user);
    }
}
