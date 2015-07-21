/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kymjs.chat.emoji;

import org.kymjs.chat.bean.Emojicon;

import java.util.ArrayList;
import java.util.List;

/**
 * emoji显示规则
 * @author kymjs (http://www.kymjs.com/) on 6/8/15.
 */
public enum DisplayRules {

    KJEMOJI0("[微笑]", 0xF0, 0x9F, 0x98, 0x81),
    KJEMOJI1("[微笑]", 0xF0, 0x9F, 0x98, 0x82),
    KJEMOJI2("[微笑]", 0xF0, 0x9F, 0x98, 0x83),
    KJEMOJI3("[微笑]", 0xF0, 0x9F, 0x98, 0x84),
    KJEMOJI4("[微笑]", 0xF0, 0x9F, 0x98, 0x85),
    KJEMOJI5("[微笑]", 0xF0, 0x9F, 0x98, 0x86),
    KJEMOJI8("[微笑]", 0xF0, 0x9F, 0x98, 0x89),
    KJEMOJI9("[微笑]", 0xF0, 0x9F, 0x98, 0x8A),
    KJEMOJI10("[微笑]", 0xF0, 0x9F, 0x98, 0x8B),
    KJEMOJI11("[微笑]", 0xF0, 0x9F, 0x98, 0x8C),
    KJEMOJI12("[微笑]", 0xF0, 0x9F, 0x98, 0x8D),
    KJEMOJI13("[微笑]", 0xF0, 0x9F, 0x98, 0x8F),
    KJEMOJI14("[微笑]", 0xF0, 0x9F, 0x98, 0x92),
    KJEMOJI16("[微笑]", 0xF0, 0x9F, 0x98, 0x93),
    KJEMOJI17("[微笑]", 0xF0, 0x9F, 0x98, 0x94),
    KJEMOJI18("[微笑]", 0xF0, 0x9F, 0x98, 0x96),
    KJEMOJI19("[微笑]", 0xF0, 0x9F, 0x98, 0x98),
    KJEMOJI20("[微笑]", 0xF0, 0x9F, 0x98, 0x9A),
    KJEMOJI21("[微笑]", 0xF0, 0x9F, 0x98, 0x9C),
    KJEMOJI22("[微笑]", 0xF0, 0x9F, 0x98, 0x9D),
    KJEMOJI23("[微笑]", 0xF0, 0x9F, 0x98, 0x9E),
    KJEMOJI24("[微笑]", 0xF0, 0x9F, 0x98, 0xA0),
    KJEMOJI25("[微笑]", 0xF0, 0x9F, 0x98, 0xA1),
    KJEMOJI26("[微笑]", 0xF0, 0x9F, 0x98, 0xA2),
    KJEMOJI27("[微笑]", 0xF0, 0x9F, 0x98, 0xA3),
    KJEMOJI28("[微笑]", 0xF0, 0x9F, 0x98, 0xA4),
    KJEMOJI29("[微笑]", 0xF0, 0x9F, 0x98, 0xA5),
    KJEMOJI30("[微笑]", 0xF0, 0x9F, 0x98, 0xA8),
    KJEMOJI31("[微笑]", 0xF0, 0x9F, 0x98, 0xA9),
    KJEMOJI32("[微笑]", 0xF0, 0x9F, 0x98, 0xAA),
    KJEMOJI33("[微笑]", 0xF0, 0x9F, 0x98, 0xAB),
    KJEMOJI34("[微笑]", 0xF0, 0x9F, 0x98, 0xAD),
    KJEMOJI35("[微笑]", 0xF0, 0x9F, 0x98, 0xB0),
    KJEMOJI36("[微笑]", 0xF0, 0x9F, 0x98, 0xB1),
    KJEMOJI37("[微笑]", 0xF0, 0x9F, 0x98, 0xB2),
    KJEMOJI38("[微笑]", 0xF0, 0x9F, 0x98, 0xB3),
    KJEMOJI39("[微笑]", 0xF0, 0x9F, 0x98, 0xB5),
    KJEMOJI40("[微笑]", 0xF0, 0x9F, 0x98, 0xB7),
    KJEMOJI41("[微笑]", 0xF0, 0x9F, 0x98, 0xB8),
    KJEMOJI42("[微笑]", 0xF0, 0x9F, 0x98, 0xB9),
    KJEMOJI43("[微笑]", 0xF0, 0x9F, 0x98, 0xBA),
    KJEMOJI44("[微笑]", 0xF0, 0x9F, 0x98, 0xBB),
    KJEMOJI45("[微笑]", 0xF0, 0x9F, 0x98, 0xBC),
    KJEMOJI46("[微笑]", 0xF0, 0x9F, 0x98, 0xBD),
    KJEMOJI47("[微笑]", 0xF0, 0x9F, 0x98, 0xBE),
    KJEMOJI48("[微笑]", 0xF0, 0x9F, 0x98, 0xBF);


    private String emojiStr;
    private byte value1;
    private byte value2;
    private byte value3;
    private byte value4;
    private byte[] value;

    private DisplayRules(String emojiStr, int value1, int value2, int value3, int value4) {
        this.emojiStr = emojiStr;
        this.value1 = (byte) value1;
        this.value2 = (byte) value2;
        this.value3 = (byte) value3;
        this.value4 = (byte) value4;
        this.value = new byte[]{
                this.value1, this.value2, this.value3, this.value4
        };
    }

    public static List<Emojicon> getAllByType() {
        List<Emojicon> datas = new ArrayList<Emojicon>(values().length);
        for (DisplayRules data : values()) {
            Emojicon emoji = new Emojicon();
            emoji.setCode(data.value);
            emoji.setName(data.emojiStr);
            datas.add(emoji);
        }
        return datas;
    }
}
