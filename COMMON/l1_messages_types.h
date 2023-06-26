
/*
 * l1_messages_types.h
 *
 *  Created on: Dec 31, 2019
 *      Author: zhenghuangkun
 */

#ifndef L1_MESSAGES_TYPES_H_
#define L1_MESSAGES_TYPES_H_

//-------------------------------------------------------------------------------------------//
// Defines to access message fields.
#define RRC_MAC_IN_SYNC_IND(mSGpTR)             (mSGpTR)->ittiMsg.rrc_mac_in_sync_ind
#define RRC_MAC_OUT_OF_SYNC_IND(mSGpTR)         (mSGpTR)->ittiMsg.rrc_mac_out_of_sync_ind




//-------------------------------------------------------------------------------------------//
// Messages between RRC and MAC layers
typedef struct RrcMacInSyncInd_s {
  uint32_t  frame;
  uint8_t   sub_frame;
  uint16_t  enb_index;
} RrcMacInSyncInd;


#endif /* L1_MESSAGES_TYPES_H_ */
