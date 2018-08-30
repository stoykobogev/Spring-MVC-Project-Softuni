package org.softuni.secondtechjmscomment.service;

import org.softuni.secondtechjmscomment.domain.entities.Comment;
import org.softuni.secondtechjmscomment.domain.models.binding.CommentBindingModel;

public interface ModelService {

    Comment mapCommentBindingModelToComment(CommentBindingModel commentBindingModel);
}
