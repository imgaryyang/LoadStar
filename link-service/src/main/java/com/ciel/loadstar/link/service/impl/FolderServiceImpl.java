package com.ciel.loadstar.link.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ciel.loadstar.link.dto.output.FolderTreeOutput;
import com.ciel.loadstar.link.entity.Folder;
import com.ciel.loadstar.link.repository.FolderRepository;
import com.ciel.loadstar.link.service.FolderService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cielqian
 * @email qianhong91@outlook.com
 * @date 2018/10/22 13:28
 */
@Service
public class FolderServiceImpl extends ServiceImpl<FolderRepository, Folder> implements FolderService {

    @Override
    public Long create(Folder folder) {

        if (folder.getParentId() == 0 || folder.getParentId() == null){
            folder.setDeep(0);
        }
        else{
            Folder parent = baseMapper.selectById(folder.getParentId());
            Assert.notNull(parent, "父级文件夹不存在");
            folder.setDeep(parent.getDeep() + 1);
        }

        baseMapper.insert(folder);
        return folder.getId();
    }

    @Override
    public List<FolderTreeOutput> queryFolderTree(Long userId) {
        List<FolderTreeOutput> folderTreeOutputs = new ArrayList<>();

        List<Folder> folders = baseMapper.queryAll(userId);
        if (folders != null && folders.size() > 0) {
            List<Folder> roots = folders.stream().filter(x -> x.getDeep() == 0).collect(Collectors.toList());
            List<Long> parentIds = new ArrayList<>();
            roots.forEach(x -> {
                FolderTreeOutput element = new FolderTreeOutput();
                element.setId(x.getId());
                element.setName(x.getName());
                element.setSystem(x.getIsSystem());
                element.setCode(x.getCode());
                folderTreeOutputs.add(element);
                parentIds.add(x.getId());
                c(folders, element);
            });
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_system", "1");
        queryWrapper.eq("is_delete", "0");
        List<Folder> systemFolders = baseMapper.selectList(queryWrapper);
        systemFolders.forEach(x -> {
            FolderTreeOutput element = new FolderTreeOutput();
            element.setId(x.getId());
            element.setName(x.getName());
            element.setSystem(x.getIsSystem());
            element.setCode(x.getCode());
            folderTreeOutputs.add(element);
        });
        return folderTreeOutputs;
    }

    @Override
    public List<FolderTreeOutput> queryFolderTree(Long folderId, Long userId) {
        List<FolderTreeOutput> folderTreeOutputs = new ArrayList<>();

        List<Folder> folders = baseMapper.queryAllUnderFolder(userId, folderId);
        if (folders != null && folders.size() > 0) {
            folders.forEach(x -> {
                FolderTreeOutput element = new FolderTreeOutput();
                element.setId(x.getId());
                element.setName(x.getName());
                element.setSystem(x.getIsSystem());
                element.setCode(x.getCode());
                folderTreeOutputs.add(element);
            });
        }
        return folderTreeOutputs;
    }

    private void c(List<Folder> all, FolderTreeOutput root){
        List<Folder> elements = all.stream().filter(y -> y.getParentId().equals(root.getId())).collect(Collectors.toList());
        elements.forEach(z -> {
            FolderTreeOutput element = new FolderTreeOutput();
            element.setId(z.getId());
            element.setName(z.getName());
            element.setSystem(z.getIsSystem());
            root.getChilds().add(element);
            c(all, element);
        });
    }
}
