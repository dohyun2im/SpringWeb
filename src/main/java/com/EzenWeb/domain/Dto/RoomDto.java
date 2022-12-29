package com.EzenWeb.domain.Dto;

import com.EzenWeb.domain.entity.RoomEntity;
import com.EzenWeb.domain.entity.RoomImg;
import com.EzenWeb.domain.entity.RoomImgRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RoomDto {
    private String rtitle;
    private String rprice;
    private String rtrans;
    private List<MultipartFile> rimg;
    private String name;
    private String lat;
    private String lng;
    @Autowired
    private RoomImgRepository roomImgRepository;
    public RoomEntity toentity() {
        ArrayList<RoomImg> list = new ArrayList<RoomImg>();
        for (MultipartFile file : rimg) {
            if(file.getOriginalFilename().equals("")){
                file.transferTo();
                RoomImg roomImg = new RoomImg();
                roomImg.setRimg(file.getOriginalFilename());
                list.add(roomImgRepository.save(roomImg));
            }
        }
        return RoomEntity.builder()
                         .rtitle(this.rtitle)
                         .rprice(this.rprice)
                         .rtrans(this.rtrans)
                         .name(this.name)
                         .lat(this.lat)
                         .lng(this.lng)
                         .roomImgslist(list)
                         .build();
    }
}
