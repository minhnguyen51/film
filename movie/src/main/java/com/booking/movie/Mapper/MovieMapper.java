package com.booking.movie.Mapper;

public class MovieMapper {
}



// ví dụ về mapper object đây dùng map enti sang dto
// nếu chưa map thì chịu khó viết code set trong service

//@Mapper(componentModel = "spring", uses = {}, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
//public interface ActualFileRequirementMapper extends EntityMapper<ActualFileRequirementDTO, ActualFileRequirement> {
//
//    default ActualFileRequirement fromId(Long id) {
//        if (id == null) {
//            return null;
//        }
//        ActualFileRequirement actualFileRequirement = new ActualFileRequirement();
//        actualFileRequirement.setActualFileId(id);
//        return actualFileRequirement;
//    }
//
//    @Mapping(target = "fileName", source = "source.actualFileName")
//    @Mapping(target = "fileFullPath", source = "source.actualFileFullpath")
//    FileInfoDTO actualFileRequirementDTOToFileInfoDTO(ActualFileRequirementDTO source);
//
//    @Mapping(target = "fileName", source = "source.actualFileName")
//    @Mapping(target = "fileFullPath", source = "source.actualFileFullpath")
//    FileInfoDTO actualFileInfoDTOToActualFileRequirement(ActualFileRequirement source);
//
//    @Mapping(target = "fileFullPath", source = "source.fileFullpath")
//    FileInfoDTO actualFileInfoDTOtoEtHeaderFile(ActualHeaderFile source);
//}