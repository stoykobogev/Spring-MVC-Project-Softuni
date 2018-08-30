package org.softuni.secondtechjmscomment.services;

import org.softuni.secondtechjmscomment.entities.Comment;
import org.softuni.secondtechjmscomment.models.binding.CommentBindingModel;

public interface ModelService {

    Comment mapCommentBindingModelToComment(CommentBindingModel commentBindingModel);
}
