package com.ravi.cache.cachestatistics.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Teachers implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 8556814059394737180L;

        private List<Teacher> teachers;
}
