package ua.romankh3.movie.tracking.service;

import ua.romankh3.movie.tracking.db.model.UserModel;

public interface EmailService {
    void sendEmail(final UserModel userModel);
}
