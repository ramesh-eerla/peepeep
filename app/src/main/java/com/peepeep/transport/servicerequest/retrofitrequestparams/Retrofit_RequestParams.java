package com.peepeep.transport.servicerequest.retrofitrequestparams;
public class Retrofit_RequestParams {
        /**
         * @Class Logindata
         * This class is used for Login
         * @dparam String userName,tversion, device;
         * Boolean AutoLogin;
         */
        public class RegistrationParams {
            String firstName, lastName, email,password,businessName,gstNumber,datecreated,modifieddate,peepeepUserId,socialNetworkIdentifier;
            boolean isBusinessUser;
            long mobileNumber;
            int userId,createduserid,modifieduserid,usertypeid,accountType,displayPicId,loginTypeId,displayPic;


            public RegistrationParams registrationData(String firstName,String lastName, String email,long mobileNumber,String password,String businessName,String gstNumber,String userId,String datecreated,String modifieddate,String createduserid,
                                                       String modifieduserid,String usertypeid,String peepeepUserId,String socialNetworkIdentifier,String accountType,String displayPicId,String loginTypeId,String displayPic ,boolean isBusinessUser){

                this.firstName = firstName;
                this.lastName=lastName;
                this.email=email;
                this.mobileNumber=mobileNumber;
                this.password=password;
                this.businessName=businessName;
                this.gstNumber=gstNumber;
                this.userId=0;//userId;
                this.datecreated=datecreated;
                this.modifieddate=modifieddate;
                this.createduserid=0;//createduserid;
                this.modifieduserid=0;//modifieduserid;
                this.usertypeid=0;//usertypeid;
                this.peepeepUserId=peepeepUserId;
                this.socialNetworkIdentifier=socialNetworkIdentifier;
                this.accountType=0;//accountType;
                this.displayPicId=0;//displayPicId;
                this.loginTypeId=0;//loginTypeId;
                this.displayPic=0;//displayPic;
                this.isBusinessUser=isBusinessUser;

                return RegistrationParams.this;
            }
        }

       /* *//**
         * @Class ReloadData
         * This class is used in multiclient scenario,to load the selected client based data
         * @dparam String sessionkey, IDNUM,userid, text;
         *//*
        public class ReloadData {
            String sessionkey, IDNUM, userid, text;

            public ReloadData ReloadData(String IDNUM, String text) {
                this.sessionkey = Commons.session_key;
                this.IDNUM = IDNUM;
                this.userid = "" + Commons.user_id;
                this.text = text;

                return ReloadData.this;
            }
        }*/

        /**
         * @Class DeleteResumeFile
         * This class is used for to remove the resume file in submiterequirement page
         * @neededparam String sessionkey
         * int UserId,MspId, ResumeSaveFileId,resumeSaveId,ClientId;
         */
        public class DeleteResumeFile {
            String sessionkey;
            int UserId, MspId, ResumeSaveFileId, resumeSaveId, ClientId;

            public DeleteResumeFile DeleteResumeFile(int ResumeSaveFileId, int resumeSaveId) {
//            this.UserId = Commons.user_id;
//            this.MspId = Commons.msp_id;
                this.ResumeSaveFileId = ResumeSaveFileId;
                this.resumeSaveId = resumeSaveId;
//            this.ClientId = Commons.client_id;
//            this.sessionkey = Commons.session_key;
                return DeleteResumeFile.this;
            }
        }

        /**
         * @Class UploadResumeFile
         * This class is used for to Upload the resume file
         * @neededparam String sessionkey
         * int UserId,MspId, resumeSaveId,ClientId;
         * JSONObject resumeDocumentFile;
         */
        public class UploadResumeFile {
            //        String sessionkey;
            int  resumeSaveId;
            //UserId, MspId,ClientId;
            ResumeDocfile resumeDocumentFile;

            public UploadResumeFile UploadResumeFile(ResumeDocfile resumeDocumentFile, int resumeSaveId) {
                this.resumeDocumentFile = resumeDocumentFile;
                this.resumeSaveId = resumeSaveId;
                return UploadResumeFile.this;
            }
        }
        /**
         * @Class ResumeDocfile
         * This class is used for to Upload the resume file
         * @neededparam String resumeDocumentFile,documentName,documentType;
         */
        public class ResumeDocfile {
            String resumeDocumentFile,documentName,documentType;
            public ResumeDocfile ResumeDocfile(String resumeDocumentFile, String documentName,String documentType) {
                this.documentName = documentType;
                this.documentType = documentName;
                this.resumeDocumentFile = resumeDocumentFile;
                return ResumeDocfile.this;
            }
        }
        /**
         * @Class UploadAdditionalDocuments
         * This class is used for to Upload the Document files
         * @neededparam String sessionkey
         * int UserId,MspId, resumeId,ClientId,candidateID,requirementId;
         * JSONObject resumeDocumentFile;
         */
        public class UploadAdditionalDocuments {

            String sessionkey,uniqueID;
            int UserId, MspId, resumeId, ClientId, candidateID, requirementId;
            ResumeDocfile resumeDocumentFile;

            public UploadAdditionalDocuments UploadAdditionalDocuments(ResumeDocfile resumeDocumentFile, int resumeId, int requirementId,String uniqueID) {
//            this.UserId = Commons.user_id;
//            this.MspId = Commons.msp_id;
                this.resumeDocumentFile = resumeDocumentFile;
                this.resumeId = resumeId;
//            this.ClientId = Commons.client_id;
                this.candidateID = 0;
                this.requirementId = requirementId;
//            this.sessionkey = Commons.session_key;
                this.uniqueID=uniqueID;
                return UploadAdditionalDocuments.this;
            }
        }

        /**
         * @Class RemoveAdditionalDocuments
         * This class is used for to Remove the Document files
         * @neededparam String sessionkey
         * int UserId,MspId, resumeId,ClientId,candidateID,requirementId;
         */
        public class RemoveAdditionalDocuments {
            String sessionkey;
            int UserId, MspId, ResumeSaveFileId, ClientId, candidateID, requirementId;

            public RemoveAdditionalDocuments RemoveAdditionalDocuments(int ResumeSaveFileId, int requirementId) {
//            this.UserId = Commons.user_id;
//            this.MspId = Commons.msp_id;
                this.ResumeSaveFileId = ResumeSaveFileId;
//            this.ClientId = Commons.client_id;
                this.candidateID = 0;
                this.requirementId = requirementId;
//            this.sessionkey = Commons.session_key;
                return RemoveAdditionalDocuments.this;
            }
        }

        /**
         * @Class SSO_Login
         * This class is used for SSO_Login
         * @neededparam String userName,tokenId,DeviceType,deviceId, version;
         */
        public class SSO_Login {
            String userName, tokenId, DeviceType, deviceId, version,Language;

            public SSO_Login getLoginRequestForSSO(String username_str, String token,
                                                   String deviceType, String deviceId, String version,String language) {

                this.userName = username_str;
                this.tokenId = token;
                this.DeviceType = deviceType;
                this.deviceId = deviceId;
                this.version = version;
                this.Language=language;
                return SSO_Login.this;
            }
        }
    }
