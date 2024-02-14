package com.app.service;

import com.app.dtos.OwnerDTO;
import com.app.entities.Owner;

public interface OwnerService {
Owner register(OwnerDTO ownerDto);
}
