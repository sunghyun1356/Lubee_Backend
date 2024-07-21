package com.Lubee.Lubee.enumset;

import lombok.Getter;

@Getter
public enum Reaction {
        LIKE("기쁨"),
        LOVE("사랑"),
        HAPPY("행복"),
        SAD("슬픔"),
        ANGRY("화남");

        private final String ReactionName;

        Reaction(String reactionName)
        {
                this.ReactionName = reactionName;
        }


}
