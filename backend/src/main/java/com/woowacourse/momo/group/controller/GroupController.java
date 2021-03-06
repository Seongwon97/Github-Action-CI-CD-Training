package com.woowacourse.momo.group.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.woowacourse.momo.auth.config.Authenticated;
import com.woowacourse.momo.group.service.GroupService;
import com.woowacourse.momo.group.service.dto.request.GroupRequest;
import com.woowacourse.momo.group.service.dto.request.GroupUpdateRequest;
import com.woowacourse.momo.group.service.dto.response.GroupIdResponse;
import com.woowacourse.momo.group.service.dto.response.GroupResponse;

@RequiredArgsConstructor
@RequestMapping("/api/groups")
@RestController
public class GroupController {

    private final GroupService groupService;

    @Authenticated
    @PostMapping
    public ResponseEntity<GroupIdResponse> create(@RequestBody GroupRequest groupRequest) {
        GroupIdResponse groupIdResponse = groupService.create(groupRequest);
        return ResponseEntity.created(URI.create("/api/groups/" + groupIdResponse.getGroupId()))
                .body(groupIdResponse);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupResponse> findById(@PathVariable Long groupId) {
        return ResponseEntity.ok(groupService.findById(groupId));
    }

    @GetMapping
    public ResponseEntity<List<GroupResponse>> findAll() {
        return ResponseEntity.ok(groupService.findAll());
    }

    @Authenticated
    @PutMapping("/{groupId}")
    public ResponseEntity<Void> update(@PathVariable Long groupId, @RequestBody GroupUpdateRequest groupUpdateRequest) {
        groupService.update(groupId, groupUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @Authenticated
    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> delete(@PathVariable Long groupId) {
        groupService.delete(groupId);
        return ResponseEntity.noContent().build();
    }
}
