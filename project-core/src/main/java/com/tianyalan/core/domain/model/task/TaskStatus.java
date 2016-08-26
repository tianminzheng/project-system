
package com.tianyalan.core.domain.model.task;

public enum TaskStatus  {

    NOT_STARTED {
        public boolean isNotStarted() {
            return true;
        }
    },

    IN_PROGRESS {
        public boolean isInProgress() {
            return true;
        }
    },

    IMPEDED {
        public boolean isImpeded() {
            return true;
        }
    },

    DONE {
        public boolean isDone() {
            return true;
        }
    };

    public boolean isDone() {
        return false;
    }

    public boolean isImpeded() {
        return false;
    }

    public boolean isInProgress() {
        return false;
    }

    public boolean isNotStarted() {
        return false;
    }
}
